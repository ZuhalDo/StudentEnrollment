package com.example.StudentEnrollment.repository;

import com.example.StudentEnrollment.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository <Instructor, Long> {

    Instructor findByEmail(String email);

    Instructor findById(long id);

    Instructor findInstructorByFirstNameAndLastName(String instructorFirstName, String instructorLastName);
}
