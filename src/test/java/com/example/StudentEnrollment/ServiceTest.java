package com.example.StudentEnrollment;

import com.example.StudentEnrollment.model.Course;
import com.example.StudentEnrollment.model.Instructor;
import com.example.StudentEnrollment.model.Student;
import com.example.StudentEnrollment.service.CourseService;
import com.example.StudentEnrollment.service.InstructorService;
import com.example.StudentEnrollment.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest

public class ServiceTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private InstructorService instructorService;
    @Test
    void testSaveCourse() {
        // Test saving a new course
        Course course = new Course();
        course.setCourseName("Test Course1");
        course.setCredits(3);
        course.setDescription("Test Description");
        courseService.saveCourse(course);

        // Retrieve the saved course and assert its existence
        Course savedCourse = courseService.getCourseById(course.getId());
        assertNotNull(savedCourse);
        assertEquals("Test Course1", savedCourse.getCourseName());
    }
    @Test
    void testFindStudentByEmail() {
        // Test finding a student by email
        Student student = new Student();
        //student.setEmail("test1@example.com");
       // studentService.saveStudent(student);

        // Retrieve the student by email and assert its existence
        Student foundStudent = studentService.getStudentByEmail("test@example.com");
        assertNotNull(foundStudent);
        assertEquals("test@example.com", foundStudent.getEmail());
    }
    @ParameterizedTest
    @ValueSource(strings = {"Active", "Inactive"})
    void testFindCoursesByStatus(String status) {
        // Test finding courses by status
        List<Course> courses = courseService.findByStatus(status);

        // Assert that the returned courses have the specified status
        for (Course course : courses) {
            assertEquals(status, course.getStatus());
        }
    }
}
