package com.example.demo.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.demo.model.dto.UserResponse;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/users/{id}")
    UserResponse getUserById(@RequestHeader("Authorization") String token, @PathVariable Long id);

    // Fallback for getUserById
    default UserResponse fallbackGetUserById(Long userId, Throwable throwable) {
        return new UserResponse(userId, "Fallback User", "fallback@example.com");
    }
}
