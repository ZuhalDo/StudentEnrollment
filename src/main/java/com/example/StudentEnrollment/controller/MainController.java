package com.example.StudentEnrollment.controller;

import com.example.StudentEnrollment.model.Instructor;
import com.example.StudentEnrollment.service.CourseService;
import com.example.StudentEnrollment.service.InstructorService;
import com.example.StudentEnrollment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

public class MainController {
    @Autowired
    private InstructorService instructorService;

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/admin/admin")
    public String admin(Model model)
    {
        System.out.println("List all courses");
        //List<Course> courseList = courseService.getAllCourses();
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("students", studentService.getAllStudents());
        return "admin"; //Return the admin page
    }

    @GetMapping("/admin/instructor/all")
    public String showAllInstructors(Model model){
        System.out.println("List all instructors");
         List<Instructor> instructors = instructorService.getAllInstructors();
        model.addAttribute("instructors", instructors);

        return "instructor_list";
    }
    @GetMapping("/admin/instructor/new")
    public String showNewInstructorPage(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "add_instructor";
    }
    @PostMapping("/admin/instructor/save")
    public String saveInstructor(@ModelAttribute("instructor") Instructor instructor) {
        instructorService.saveInstructor(instructor);
        return "redirect:/admin/instructor/all";

    }
}
