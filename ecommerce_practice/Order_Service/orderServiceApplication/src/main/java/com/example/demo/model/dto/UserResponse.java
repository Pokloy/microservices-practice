package com.example.demo.model.dto;

import lombok.Data;

@Data
public class UserResponse {
    public UserResponse(Long userId, String string, String string2) {
		// TODO Auto-generated constructor stub
	}
	private Long id;
    private String name;
    private String email;
}
