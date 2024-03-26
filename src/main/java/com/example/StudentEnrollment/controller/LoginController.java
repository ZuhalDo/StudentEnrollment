package com.example.StudentEnrollment.controller;

import com.example.StudentEnrollment.model.Course;
import com.example.StudentEnrollment.model.Student;
import com.example.StudentEnrollment.service.CourseService;
import com.example.StudentEnrollment.service.StudentService;
import com.example.StudentEnrollment.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class LoginController {

    private  static final Logger logger= LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;


    @GetMapping("/login")
    public String login(Model model)
    {
        logger.info("Login page accessed");
        return "login"; // Return the login page
    }

    @GetMapping("/admin")
    public String admin(Model model)
    {
        logger.info("Admin dashboard accessed");
         //List<Course> courseList = courseService.getAllCourses();
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("students", studentService.getAllStudents());
        return "admin"; //Return the admin page
    }
    @GetMapping("/student")
    public String student(Model model, Principal principal){
        logger.info("Student dashboard accessed");
        // Get the currently logged-in student
        String email = principal.getName();
        Student student = studentService.getStudentByEmail(email);
        // Fetch the enrolled courses for the logged-in student
        List<Course> enrolledCourses = studentService.getEnrolledCourses(student);
        // Add the enrolled courses to the model
        model.addAttribute("enrolledCourses", enrolledCourses);

       return "studentD"; //Return the student page
    }
    @GetMapping("/instructor")
    public String instructor(Model model){
        logger.info("Instructor dashboard accessed");
        return "redirect:/instructor/"; //Return the instructor page
    }
    @ResponseBody
    @GetMapping("/logoutSuccess")
    public String logoutResponse()
    {
        logger.info("Logout success");
        System.out.println("IN  LoginController->logoutResponse()");
        return "Logged Out!!!!";
    }


}
