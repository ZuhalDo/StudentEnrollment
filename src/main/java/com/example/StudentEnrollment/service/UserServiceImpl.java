package com.example.StudentEnrollment.service;
import com.example.StudentEnrollment.model.Role;
import com.example.StudentEnrollment.model.RoleType;
import com.example.StudentEnrollment.model.Student;
import com.example.StudentEnrollment.model.User;
import com.example.StudentEnrollment.repository.UserRegistrationDto;
import com.example.StudentEnrollment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private StudentService studentService;


    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User save(UserRegistrationDto registration){
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));

       // user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        Set<Role> roles = new HashSet<>();
        for (String roleName : registration.getRoles()) {
            RoleType roleType = RoleType.valueOf(roleName);
            Role role = new Role();
            role.setName(roleType);
            roles.add(role);
        }
        user.setRoles(roles);
        user= userRepository.save(user); //save the user

        if(roles.stream().anyMatch(role -> role.getName() == RoleType.ROLE_STUDENT)){
            Student student = new Student();
            student.setFirstname(user.getFirstName());
            student.setLastname(user.getLastName());
            student.setEmail(user.getEmail());
            student.setPassword(user.getPassword());
            student.setUser(user); // Associate the student with the user
            studentService.saveStudent(student); // Save the student
        }
        return user;
    }

    @Override
    public Long getStudentIdByUsername(String username) {
        User user = userRepository.findByEmail(username);
        if (user != null && user.getStudent() != null) {
            return user.getStudent().getId();
        } else {
            throw new IllegalArgumentException("User or student not found for username: " + username);
        }
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .collect(Collectors.toList());
    }

}

