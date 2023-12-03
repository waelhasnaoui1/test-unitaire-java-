package com.alibou.demo.student.impl;

import com.alibou.demo.student.*;
import org.aspectj.lang.annotation.Before;
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
import static org.mockito.ArgumentMatchers.any;

class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper mapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {

        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setId(null);
        studentRequest.setFirstname("wael");

        Student student = new Student();
        student.setId(null);
        student.setFirstname("wael");

        Mockito.when(mapper.toStudent(studentRequest)).thenReturn(student);

        studentService.save(studentRequest);

        Mockito.verify(mapper,Mockito.times(1)).toStudent(studentRequest);

        Mockito.verify(studentRepository,Mockito.times(1)).save(student);
    }

    @Test
    void findById() {

        int studentId = 1;

        Student student = new Student();

        student.setId(studentId);
        student.setFirstname("Wael");

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setFirstname("Wael");

        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        Mockito.when(mapper.toStudentDto(student)).thenReturn(studentResponse);

        StudentResponse response = studentService.findById(studentId);

        Mockito.verify(studentRepository,Mockito.times(1)).findById(studentId);

        assertEquals(studentResponse,response);

    }

    @Test
    void find_by_id_student_not_exist(){
        int studentId = 1;

        StudentResponse studentResponse = new StudentResponse();

        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        Mockito.when(mapper.toStudentDto(any())).thenReturn(studentResponse);

        var response = studentService.findById(studentId);

        assertEquals(studentResponse,response);

    }


    @Test
    void findAll() {

        List<Student> students = new ArrayList<>();

        Student student1 = Student.builder().id(1).firstname("wael").build();
        Student student2 = Student.builder().id(2).firstname("sarra").build();

        students.add(student1);
        students.add(student2);

        List<StudentResponse> studentsResponse = new ArrayList<>();

        StudentResponse student1Res = StudentResponse.builder().firstname("wael").build();
        StudentResponse student2Res = StudentResponse.builder().firstname("sarra").build();

        studentsResponse.add(student1Res);
        studentsResponse.add(student2Res);

        Mockito.when(studentRepository.findAll()).thenReturn(students);

        Mockito.when(mapper.toStudentDto(student1)).thenReturn(student1Res);
        Mockito.when(mapper.toStudentDto(student2)).thenReturn(student2Res);

        var response = studentService.findAll();

        assertEquals(studentsResponse,response);

    }

    @Test
    void deleteById() {
        int studentId = 1;

        studentService.deleteById(studentId);

        Mockito.verify(studentRepository,Mockito.times(1)).deleteById(studentId);

    }
}