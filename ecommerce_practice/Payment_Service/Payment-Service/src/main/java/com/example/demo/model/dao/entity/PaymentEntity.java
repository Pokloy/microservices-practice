package com.example.demo.model.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_payment")
@Data
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="order_id")
    private Long orderId;
    
    @Column(name="user_id")
    private Long userId;
    
    @Column(name="amount")
    private Double amount;
    
    @Column(name="status")
    private String status;
}
