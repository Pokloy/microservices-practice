package com.example.demo.model.dto;

import lombok.*;

@Data
public class OrderCreatedEvent {
    private String orderId;

    private String userId;

    private Double amount;

    private String status;
}
