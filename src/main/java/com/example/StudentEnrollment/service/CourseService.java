package com.example.StudentEnrollment.service;

import com.example.StudentEnrollment.model.Course;
import com.example.StudentEnrollment.model.Instructor;
import com.example.StudentEnrollment.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id){
        Course course=courseRepository.findById(id).get();
        if(course !=null){
            return course;
        }else{
            return null;
        }
    }
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }

    // Method to get all available courses
    public List<Course> getAvailableCourses() {

        return courseRepository.findByStatus("active");
    }
    public List<Course> getCoursesByInstructorEmail(String instructorEmail) {
        return courseRepository.findByInstructorEmail(instructorEmail);
    }

    public List<Course> findByStatus(String status) {
        return courseRepository.findByStatus(status);
    }
}
