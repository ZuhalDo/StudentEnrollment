package com.example.StudentEnrollment;

import com.example.StudentEnrollment.model.Instructor;
import com.example.StudentEnrollment.repository.InstructorRepository;
import com.example.StudentEnrollment.service.InstructorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@ActiveProfiles("test")
public class InstructorRepositoryTest {

    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private InstructorService instructorService;

    @Test
    void testFindByEmail(){

        Instructor foundInstructor=instructorService.findByEmail("jack@gmail.com");
        //then
        assertNotNull(foundInstructor);
        assertEquals("jack@gmail.com",foundInstructor.getEmail());
    }

    @Test
    void testFindInstructorByFirstNameAndLastName(){

        //When
        Instructor foundInstructor=instructorRepository.findInstructorByFirstNameAndLastName
                ("Carlos","Martinez");

        //then
        assertNotNull(foundInstructor);
        assertEquals("Carlos",foundInstructor.getFirstName());
        assertEquals("Martinez",foundInstructor.getLastName());
    }
}
