package com.example.StudentEnrollment.config;

import com.example.StudentEnrollment.service.UserService;
import com.example.StudentEnrollment.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.Collection;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserServiceImpl userService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/sign-up").permitAll()
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .requestMatchers("/student").hasRole("STUDENT")
                                .requestMatchers("/instructor").hasRole("INSTRUCTOR")
                                .anyRequest().authenticated()

                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login") // Specify your custom login page URL
                                .permitAll()
                                .successHandler((request, response, authentication) -> {
                                    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                                    authorities.forEach(authority -> {
                                        if (authority.getAuthority().equals("ROLE_ADMIN")) {
                                            try {
                                                response.sendRedirect("/admin");
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        } else if (authority.getAuthority().equals("ROLE_STUDENT")) {
                                            try {
                                                response.sendRedirect("/student");
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        } else if (authority.getAuthority().equals("ROLE_INSTRUCTOR")) {
                                            try {
                                                response.sendRedirect("/instructor");
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                })


                ).logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/login?logout").permitAll()
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                );;

        return http.build();
    }


    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}