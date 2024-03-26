package com.example.StudentEnrollment.service;

import com.example.StudentEnrollment.model.Course;
import com.example.StudentEnrollment.model.Student;
import com.example.StudentEnrollment.repository.CourseRepository;
import com.example.StudentEnrollment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    public Student getStudentById(Long id){

         return studentRepository.findById(id).get();
      }
    public List<Course> getEnrolledCourses(Student student){
        return student.getCourses();
    }
    public Student getStudentByEmail(String email){
        return studentRepository.findByEmail(email);
    }
     public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
    public void enrollStudentInCourse(Long studentId, Long courseId){
        Student student=studentRepository.findById(studentId).get();
        Course course=courseRepository.findById(courseId).get();

        student.addCourse(course);
        studentRepository.save(student);

    }

    public void dropCourse(Long studentId, Long courseId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            // Student found, proceed with dropping the course
            Student student = optionalStudent.get();
            List<Course> courses = student.getCourses();
            courses.removeIf(course -> course.getId() == courseId);
            student.setCourses(courses);
            studentRepository.save(student);
        } else {
            // Student not found, throw an exception
            throw new IllegalArgumentException("Student not found with ID: " + studentId);
        }
    }
}
