package com.example.StudentEnrollment;

import com.example.StudentEnrollment.model.Student;
import com.example.StudentEnrollment.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("Test")
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testFindByEmail(){
        Student foundStudent= studentRepository.findByEmail("preston@gmail.com");
        assertNotNull(foundStudent);
        assertEquals("preston@gmail.com",foundStudent.getEmail());
    }
}
