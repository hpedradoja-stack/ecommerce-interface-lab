package com.example.ecommerce.controller;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // REGISTER (PUBLIC)
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        // hash password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return "User registered successfully!";
    }
}

.requestMatchers("/api/v1/auth/register").permitAll()
.requestMatchers("/login", "/logout").permitAll()
  POST http://localhost:8080/api/v1/auth/register
{
  "username": "admin",
  "password": "123456",
  "role": "ADMIN"
}
POST http://localhost:8080/login
  username=admin
password=123456
  POST http://localhost:8080/logout
