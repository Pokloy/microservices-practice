package com.example.demo.model.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dao.PaymentRepository;
import com.example.demo.model.dao.entity.PaymentEntity;
import com.example.demo.model.dto.OrderCreatedEvent;
import com.example.demo.model.service.PaymentService;

@Service
public class PaymentServiceImpl extends PaymentService {
    
	@Autowired
	PaymentRepository paymentRepository;
	
	@Override
    public PaymentEntity processPayment(OrderCreatedEvent orderEvent) {
        PaymentEntity payment = new PaymentEntity();
        payment.setOrderId(orderEvent.getOrderId());
        payment.setUserId(orderEvent.getUserId());
        payment.setAmount(orderEvent.getAmount());
        payment.setStatus("SUCCESS");

        return paymentRepository.save(payment);
    }
}
