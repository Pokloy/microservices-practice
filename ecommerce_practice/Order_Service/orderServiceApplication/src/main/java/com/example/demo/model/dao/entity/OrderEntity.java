package com.example.demo.model.dao.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "tb_orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary Key

    @Column(name = "user_id", nullable = false)
    private Long userId; // ID of the user who placed the order

    @Column(name = "product_id", nullable = false)
    private Long productId; // ID of the purchased product

    @Column(name = "quantity", nullable = false)
    private Integer quantity; // Number of units ordered

    @Column(name = "total_price", nullable = false)
    private Double totalPrice; // Total price of the order

    @Column(name = "status", nullable = false)
    private String status; // e.g., "PENDING", "COMPLETED", "CANCELLED"

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate; // Timestamp of when the order was placed
    
    @Column(name = "customer_name", nullable = false)
    private String customerName; // Store customer name

}
