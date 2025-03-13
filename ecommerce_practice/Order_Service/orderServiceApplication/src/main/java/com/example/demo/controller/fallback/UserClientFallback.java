package com.example.demo.controller.fallback;

import org.springframework.stereotype.Component;
import com.example.demo.controller.UserClient;
import com.example.demo.model.dto.UserResponse;

@Component  // Correct annotation for Feign fallbacks
public class UserClientFallback implements UserClient {
    @Override
    public UserResponse getUserById(Long userId) {
        return new UserResponse(userId, "Fallback User", "fallback@example.com");
    }
}
