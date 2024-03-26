package com.example.StudentEnrollment;

import com.example.StudentEnrollment.model.Course;
import com.example.StudentEnrollment.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("Test")
public class CourseRepositoryTest {
    @Autowired
    CourseRepository courseRepository;

    @Test
    void testFindByStatus(){

        String status="Active";
       // CourseRepository foundCourse= (CourseRepository) courseRepository.findByStatus("Active");
        List<Course> courses= courseRepository.findByStatus(status);
        assertNotNull(courses);

    }
}
