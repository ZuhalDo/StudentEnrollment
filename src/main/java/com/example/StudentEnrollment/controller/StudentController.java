package com.example.StudentEnrollment.controller;

import com.example.StudentEnrollment.model.Student;
import com.example.StudentEnrollment.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller

public class StudentController {


    private StudentService studentService;

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }


    @GetMapping("/student/all")
    public String showAllStudents(Model model){
        List<Student> studentList = studentService.getAllStudents();
        model.addAttribute("student", studentService.getAllStudents());
        return "student_list";
    }

    @GetMapping("/student/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        System.out.println("IN  StudentController->editStudentForm()");
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/student/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student,
                                Model model) {

        // get student from database by id
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstname(student.getFirstname());
        existingStudent.setLastname(student.getLastname());
        existingStudent.setEmail(student.getEmail());

        // save updated student object
        studentService.updateStudent(existingStudent);
        return "redirect:/student/all";
    }


    @GetMapping("/student/new")
    public String createStudentForm(Model model) {
        System.out.println("IN  StudentController->createStudentForm()");
        // create student object to hold student form data
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";

    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
         System.out.println("IN  StudentController->saveStudent()");
        studentService.saveStudent(student);
        return "redirect:/student/all";
    }
    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        System.out.println("IN  StudentController->deleteStudent()");
        studentService.deleteStudentById(id);
        return "redirect:/student/all";
    }


}
