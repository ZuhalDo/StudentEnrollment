package com.example.StudentEnrollment.controller;

import com.example.StudentEnrollment.model.User;
import com.example.StudentEnrollment.repository.UserRegistrationDto;
import com.example.StudentEnrollment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/sign-up")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        System.out.println("IN  UserRegController->UserRegistrationDto()");
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user",new UserRegistrationDto());
        System.out.println("IN  UserRegController->showRegistrationForm()");
        return "sign-up";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result){

        System.out.println("IN  POST MAPPING UserRegController->registerUserAccount()");
        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        System.out.println("result:"+result.toString());

        if (result.hasErrors()){
            System.out.println("result:"+result.toString());
            return "sign-up";
        }


        userService.save(userDto);
        return "redirect:/login";
    }
}