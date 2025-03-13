package com.example.demo.model.dto;

import lombok.Data;

@Data
public class UserResponse {
	private Long id;
    private String name;
    private String email;
    public UserResponse(Long userId, String string, String string2) {
		this.id = userId;
		this.name = string;
		this.email = string2;
	}
}
