package com.alibou.demo.subject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubjectMapperTest2 {

    private SubjectMapper mapper = new SubjectMapper();

    @Test
    public void should_successfully_transform_subject_request_to_subject(){
        SubjectRequest subjectRequest = new SubjectRequest();
        subjectRequest.setId(null);
        subjectRequest.setName("Math");

        Subject result = mapper.toSubject(subjectRequest);

        Assertions.assertEquals(result.getId(),null);
        //Or we can use assertNull() directly
        Assertions.assertNull(result.getId());
        Assertions.assertEquals(result.getName(),"Math");
        assertNull(result.getStudents());
        assertNull(result.getChapters());

    }

    @Test
    public void should_successfully_transform_null_subject_request_to_subject(){

        Subject result = mapper.toSubject(null);
        Assertions.assertNotNull(result);
        Assertions.assertNull(result.getId());
        assertNull(result.getName());
        assertNull(result.getStudents());
        assertNull(result.getChapters());

    }

    @Test
    public void should_successfully_transform_subject_to_subject_response(){
        Subject subject = new Subject();
        subject.setName("Math");
        subject.setId(null);

        SubjectResponse subjectResponse = mapper.toSubjectResponse(subject);

        Assertions.assertEquals("Math",subjectResponse.getName());

    }

    @Test
    public void should_successfully_transform_null_subject_to_subject_response(){

        SubjectResponse subjectResponse = mapper.toSubjectResponse(null);

        Assertions.assertNull(subjectResponse.getName());

    }

}