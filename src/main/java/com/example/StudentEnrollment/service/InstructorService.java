package com.example.StudentEnrollment.service;

import com.example.StudentEnrollment.model.Course;
import com.example.StudentEnrollment.model.Instructor;
import com.example.StudentEnrollment.repository.CourseRepository;
import com.example.StudentEnrollment.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }
    public void saveInstructor(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }
    public Instructor findByEmail(String email) {
        return instructorRepository.findByEmail(email);
    }
    public List<Course> getCoursesByInstructorEmail(String email) {
        Instructor instructor = instructorRepository.findByEmail(email);
        return instructor.getCourses();
    }



    public Instructor findInstructorById(Long instructorId) {
        return instructorRepository.findById(instructorId).orElse(null);
    }

    public Instructor findInstructorByFirstNameAndLastName(String instructorFirstName, String instructorLastName) {
        return instructorRepository.findInstructorByFirstNameAndLastName(instructorFirstName,instructorLastName);
    }
}