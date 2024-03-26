package com.example.StudentEnrollment;

import com.example.StudentEnrollment.model.User;
import com.example.StudentEnrollment.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindByEmail(){

        User foundUser=userRepository.findByEmail("zuhal0202@gmail.com");
        assertNotNull(foundUser);
        assertEquals("zuhal0202@gmail.com",foundUser.getEmail());
    }
}
