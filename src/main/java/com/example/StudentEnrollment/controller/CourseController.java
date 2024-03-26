package com.example.StudentEnrollment.controller;

import com.example.StudentEnrollment.model.Course;
import com.example.StudentEnrollment.model.Instructor;
import com.example.StudentEnrollment.model.Student;
import com.example.StudentEnrollment.repository.CourseRepository;
import com.example.StudentEnrollment.service.CourseService;
import com.example.StudentEnrollment.service.InstructorService;
import com.example.StudentEnrollment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private CourseRepository courseRepository;

    public CourseController(CourseService courseService, InstructorService instructorService){
        super();
        this.courseService=courseService;
        this.instructorService=instructorService;
    }
    @GetMapping("/admin")
    public String admin(Model model){
        return "admin";
    }
    //Display all courses
    @GetMapping("/all")
    public String showAllCourses(Model model){
        System.out.println("List all courses");
       // List<Course> courseList = courseService.getAllCourses();
        model.addAttribute("courses", courseService.getAllCourses());

        return "courses";
    }

    //Display course details by Id
    @GetMapping("/{id}")
    public String showCourseDetails(@PathVariable("id") Long id, Model model){
        Course course=courseService.getCourseById(id);
        model.addAttribute("course",course);
        return "course-details";
    }

    @GetMapping("/new")
    public String createCourse(Model model) {
        System.out.println("IN  CourseController->createCourse()");
        // create course object to hold course form data
        Course course = new Course();
        model.addAttribute("course", course);
        List<Instructor> instructors = instructorService.getAllInstructors();
        model.addAttribute("instructors", instructors);
        return "add_course";

    }
    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course") Course course) {
        System.out.println("IN CourseController->saveCourse()");
        // Fetch instructor by ID
        Long instructorId = course.getInstructorId();
        Instructor instructor = instructorService.findInstructorById(instructorId);
        // Set instructor in the course entity
        course.setInstructor(instructor);
        courseService.saveCourse(course);

        return "redirect:/admin";
    }

   /* @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course")  Course course,
                             @RequestParam("instructorFirstName") String instructorFirstName,
                             @RequestParam("instructorLastName") String instructorLastName) {
        System.out.println("IN  CourseController->saveCourse()");
        // Fetch instructor ID by name
        //Long instructorId = instructorService.findIdByFirstNameAndLastName(instructorFirstName, instructorLastName);
        Instructor instructor = instructorService.findInstructorByFirstNameAndLastName(instructorFirstName, instructorLastName);
        // Set instructor ID in the course entity
        course.setInstructor(instructor);
        courseService.saveCourse(course);

        return "redirect:/admin";
    }*/


    // Show form to edit course
    @GetMapping("/edit/{id}")
    public String showEditCourseForm(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        // Retrieve the list of instructors
        List<Instructor> instructors = instructorService.getAllInstructors();
        model.addAttribute("course", course);
        model.addAttribute("instructors", instructors);
        return "edit_course";
    }

    // Save edited course
    @PostMapping("/{id}")
    public String editCourse(@PathVariable("id") Long id, @ModelAttribute("course") Course course) {
        course.setId(id); // Ensure correct ID is set
        courseService.saveCourse(course);
        return "redirect:/courses/all";
    }
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        System.out.println("delete course");
        courseService.deleteCourseById(id);
        return "redirect:/courses/all";
    }

}
