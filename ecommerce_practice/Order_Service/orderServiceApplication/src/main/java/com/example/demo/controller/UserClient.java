package com.example.demo.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.controller.fallback.UserClientFallback;
import com.example.demo.model.dto.UserResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name = "USER-SERVICE", path = "/users", fallback = UserClientFallback.class)
public interface UserClient {
    @GetMapping("/{userId}")
    @CircuitBreaker(name = "userServiceBreaker", fallbackMethod = "fallbackGetUserById")
    UserResponse getUserById(@PathVariable("userId") Long userId);
    
    default UserResponse fallbackGetUserById(Long userId, Throwable throwable) {
        return new UserResponse(userId, "Fallback User", "fallback@example.com");
    }
}