package com.example.StudentEnrollment.repository;

import com.example.StudentEnrollment.model.Course;
import com.example.StudentEnrollment.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
   // List<Course> getEnrolledCourses(Student student);
}
