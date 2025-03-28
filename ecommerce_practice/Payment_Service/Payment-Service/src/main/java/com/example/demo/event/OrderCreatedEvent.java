package com.example.demo.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class OrderCreatedEvent {
    private String orderId;

    private String userId;

    private Double amount;

    private String status;
}
