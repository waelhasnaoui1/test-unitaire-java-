package com.alibou.demo.subject.impl;

import com.alibou.demo.student.Student;
import com.alibou.demo.student.StudentMapper;
import com.alibou.demo.student.StudentRepository;
import com.alibou.demo.subject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SubjectServiceImplTest2 {

    @Mock
    private SubjectRepository subjectRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private SubjectMapper mapper;
    @InjectMocks
    private SubjectServiceImpl subjectService;

    @BeforeEach
    private void setUp(){
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void firstTest(){
//        var result = subjectService.findAll();
//        System.out.println(result);
//    }

    @Test
    public void should_save_subject_successfully(){
        SubjectRequest subjectRequest = new SubjectRequest();
        subjectRequest.setName("Math");
        subjectRequest.setId(null);

        Subject subject = new Subject();

        subject.setId(null);
        subject.setName("Math");

        Mockito.when(mapper.toSubject(subjectRequest)).thenReturn(subject);

        subjectService.save(subjectRequest);

        Mockito.verify(subjectRepository,Mockito.times(1)).save(subject);

    }

    @Test
    public void should_assign_subject_to_student(){
        int studentId=1;
        int subjectId = 1;
        Subject subject = new Subject();
        subject.setId(subjectId);

        Mockito.when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(subject));

        Student student = new Student();
        student.setSubjects(new ArrayList<>());
        subject.setId(studentId);
        subject.setStudents(new ArrayList<>());

        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        subjectService.assignSubjectToStudent(subjectId,studentId);


        assertTrue(subject.getStudents().contains(student));

        assertTrue(student.getSubjects().contains(subject));


        Mockito.verify(studentRepository,Mockito.times(1)).save(student);

        Mockito.verify(subjectRepository,Mockito.times(1)).save(subject);


    }

    @Test
    void find_by_id_subject(){
        int subjectId= 1;

        Subject subject = new Subject();
        subject.setId(1);
        subject.setName("Math");

        Mockito.when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(subject));

        SubjectResponse subjectResponse = new SubjectResponse();
        subjectResponse.setName("Math");

        Mockito.when(mapper.toSubjectResponse(subject)).thenReturn(subjectResponse);

        SubjectResponse response = subjectService.findById(subjectId);

        Mockito.verify(subjectRepository,Mockito.times(1)).findById(subjectId);

        assertEquals(subjectResponse,response);

    }

    @Test
    void find_subject_by_id_throw_an_error (){

    }

    @Test
    void findAll(){
        List<Subject> subjects = new ArrayList<>();

        Subject subject1 = Subject.builder().id(1).name("Math").build();
        Subject subject2 = Subject.builder().id(2).name("Phy").build();

        subjects.add(subject1);
        subjects.add(subject2);

        List<SubjectResponse> subjectsResponse = new ArrayList<>();

        SubjectResponse subject1Response = SubjectResponse.builder().name("Math").build();
        SubjectResponse subject2Response = SubjectResponse.builder().name("Phy").build();

        subjectsResponse.add(subject1Response);
        subjectsResponse.add(subject2Response);

        Mockito.when(mapper.toSubjectResponse(subject1)).thenReturn(subject1Response);
        Mockito.when(mapper.toSubjectResponse(subject2)).thenReturn(subject2Response);

        Mockito.when(subjectRepository.findAll()).thenReturn(subjects);

        var response = subjectService.findAll();

        Mockito.verify(subjectRepository,Mockito.times(1)).findAll();

        assertEquals(subjectsResponse,response);



    }

    @Test
    void deleteById() {
        int subjectId = 1;

        subjectRepository.deleteById(subjectId);

        Mockito.verify(subjectRepository,Mockito.times(1)).deleteById(subjectId);

    }





}