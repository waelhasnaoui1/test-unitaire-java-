package com.alibou.demo.student;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    StudentMapper mapper = new StudentMapper();

    @Test
    public void test_to_student_dto() {


        Student student = new Student();
        student.setFirstname("wael");
        student.setLastname("hasnaoui");
        student.setAge(25);
        student.setSubjects(new ArrayList<>());


        StudentResponse studentResponse = mapper.toStudentDto(student);

        assertEquals("wael", studentResponse.getFirstname());
        assertEquals("hasnaoui", studentResponse.getLastname());
        assertEquals(25, studentResponse.getAge());
        assertEquals(0,studentResponse.getNbrSubjects());

    }


    @Test
    public void test_to_student() {

        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setId(1);
        studentRequest.setFirstname("wael");
        studentRequest.setLastname("hasnaoui");
        studentRequest.setAge(22);




        Student student = mapper.toStudent(studentRequest);

        assertEquals(1, student.getId());
        assertEquals("wael", student.getFirstname());
        assertEquals("hasnaoui", student.getLastname());
        assertEquals(22, student.getAge());
    }

}