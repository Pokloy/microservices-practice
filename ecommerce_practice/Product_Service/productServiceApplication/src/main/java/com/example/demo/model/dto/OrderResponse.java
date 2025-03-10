package com.example.demo.model.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderResponse {
    private Long id; 

    private Long userId; 
    
    private Long productId; 

    private Integer quantity; 

    private Double totalPrice; 

    private String status; 

    private LocalDateTime orderDate; 
    
    private String customerName; 
}
