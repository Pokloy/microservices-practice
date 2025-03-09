package com.example.demo.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.dto.UserResponse;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {
    @GetMapping("/users/{userId}")
    UserResponse getUserById(@PathVariable Long userId);
}