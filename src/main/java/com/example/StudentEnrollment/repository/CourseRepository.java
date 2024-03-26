package com.example.StudentEnrollment.repository;

import com.example.StudentEnrollment.model.Course;
import com.example.StudentEnrollment.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByStatus(String status);

    List<Course> findByInstructor(Instructor instructor);
    List<Course> findByInstructorEmail(String instructorEmail);
}
