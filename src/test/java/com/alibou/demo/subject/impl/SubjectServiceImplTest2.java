package com.alibou.demo.subject.impl;

import com.alibou.demo.student.Student;
import com.alibou.demo.student.StudentMapper;
import com.alibou.demo.student.StudentRepository;
import com.alibou.demo.subject.Subject;
import com.alibou.demo.subject.SubjectMapper;
import com.alibou.demo.subject.SubjectRepository;
import com.alibou.demo.subject.SubjectRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
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





}