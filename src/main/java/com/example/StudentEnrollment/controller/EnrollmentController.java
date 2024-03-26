package com.example.StudentEnrollment.controller;

import com.example.StudentEnrollment.model.Course;
import com.example.StudentEnrollment.model.Student;
import com.example.StudentEnrollment.service.CourseService;
import com.example.StudentEnrollment.service.StudentService;
import com.example.StudentEnrollment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
public class EnrollmentController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final UserService userService;

    @Autowired
    public EnrollmentController(StudentService studentService, CourseService courseService, UserService userService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.userService=userService;
    }

    @GetMapping("/enroll")
    public String showEnrollmentPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Long studentId = userService.getStudentIdByUsername(username);
        model.addAttribute("studentId", studentId); // Add studentId to the model
        List<Course> availableCourses = courseService.getAvailableCourses();
        model.addAttribute("availableCourses", availableCourses);
        return "enroll";
    }

     //Enroll the courses
    @PostMapping("/{studentId}/enroll")
    public String enrollStudent(@PathVariable("studentId") Long studentId, @RequestParam("selectedCourseId") Long courseId){
        studentService.enrollStudentInCourse(studentId,courseId);
        return "redirect:/student";
    }
    //List the enrolled courses
    @GetMapping("/drop")
    public String showDropPage(Model model, Principal principal) {
       /* String email=principal.getName();
        Long studentId = userService.getStudentIdByUsername(email);
        model.addAttribute("studentId",studentId);
        List<Course> enrolledCourses =studentService.getEnrolledCourses(email);

        model.addAttribute("enrolledCourses", enrolledCourses);
        return "drop";*/
        String email = principal.getName();
        Student student = studentService.getStudentByEmail(email);
        Long studentId = userService.getStudentIdByUsername(email);
        model.addAttribute("studentId",studentId);
        List<Course> enrolledCourses = student.getCourses();

        model.addAttribute("enrolledCourses", enrolledCourses);
        return "drop";
    }
    //Drop the courses
    @PostMapping("/{studentId}/drop")
    public String dropCourse(@PathVariable("studentId") Long studentId, @RequestParam("selectedCourseId") Long courseId){
       Student student=studentService.getStudentById(studentId);
        studentService.dropCourse(studentId,courseId);
        return "redirect:/student";
    }
}


