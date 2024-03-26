package com.example.StudentEnrollment.service;

import com.example.StudentEnrollment.model.Role;
import com.example.StudentEnrollment.model.User;
import com.example.StudentEnrollment.repository.UserRegistrationDto;

import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    Long getStudentIdByUsername(String username);
    User save(UserRegistrationDto registration);
}