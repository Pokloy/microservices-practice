package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.dao.UserRepository;
import com.example.demo.model.dao.entity.UserEntity;
import com.example.demo.model.service.UserService;
import com.example.demo.util.JwtUtil;

@RestController
public class AuthController {
    
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    UserRepository userRepo;

    public AuthController(UserService userService, JwtUtil jwtUtil, 
    		@Lazy AuthenticationManager authenticationManager, 
                           PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }
    
	
    @PostMapping("/register")
    public String registerUser(@RequestBody UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); 
        userService.createUser(user);
        return "User registered successfully!";
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserEntity user) {
    	Map<String, String> response = new HashMap<>();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );
        if (authentication.isAuthenticated()) {
        	String accessToken = jwtUtil.generateToken(user.getEmail()); // Generate JWT if valid
        	response.put("access_token", accessToken);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
