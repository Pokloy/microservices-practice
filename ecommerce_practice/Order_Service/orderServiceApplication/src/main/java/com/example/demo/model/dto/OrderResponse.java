package com.example.demo.model.dto;

import com.example.demo.model.dao.entity.OrderEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {
    private OrderEntity order;
    private UserResponse user;
}
