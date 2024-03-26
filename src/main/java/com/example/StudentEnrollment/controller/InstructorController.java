package com.example.StudentEnrollment.controller;

import com.example.StudentEnrollment.model.Course;
import com.example.StudentEnrollment.model.Instructor;
import com.example.StudentEnrollment.service.CourseService;
import com.example.StudentEnrollment.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
    private InstructorService instructorService;

    private CourseService courseService;
    @Autowired
    public InstructorController(InstructorService instructorService,CourseService courseService) {
        this.courseService=courseService;
        this.instructorService = instructorService;
    }

    @GetMapping("/")
    public String showInstructorCourses(Model model, Principal principal) {
        String instructorEmail = principal.getName();
        List<Course> instructorCourses = courseService.getCoursesByInstructorEmail(instructorEmail);
        model.addAttribute("courses", instructorCourses);
        return "instructor";
    }
    @GetMapping("/edit/{id}")
    public String showEditCourseForm(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getCourseById(id);

        model.addAttribute("course", course);
        return "instructor_editCourse";
    }

    // Save edited course
    @PostMapping("/{id}")
    public String editCourse(@PathVariable("id") Long courseId, @ModelAttribute("course") Course updatedCourse) {
        Course existingCourse = courseService.getCourseById(courseId);
        existingCourse.setCourseName(updatedCourse.getCourseName());
        existingCourse.setCourseCode(updatedCourse.getCourseCode());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setCapacity(updatedCourse.getCapacity());
        courseService.saveCourse(existingCourse); // Save the updated course
        return "redirect:/instructor";
    }
}
