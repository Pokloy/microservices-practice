package com.example.demo.model.service.impl;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.event.OrderCreatedEvent;
import com.example.demo.model.dao.PaymentRepository;
import com.example.demo.model.dao.entity.PaymentEntity;
import com.example.demo.model.dto.PaymentSuccessEvent;
import com.example.demo.model.service.PaymentService;

@Service
public class PaymentServiceImpl extends PaymentService {
    
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Override
    public void processPayment1(OrderCreatedEvent orderCreatedEvent) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setAmount(orderCreatedEvent.getAmount());
        paymentEntity.setOrderId(Long.parseLong(orderCreatedEvent.getOrderId()));
        paymentEntity.setUserId(Long.parseLong(orderCreatedEvent.getUserId()));
        paymentEntity.setStatus(orderCreatedEvent.getStatus());
        paymentRepository.saveAndFlush(paymentEntity);
    	
    	// Simulate payment processing
        System.out.println("Processing payment for Order ID: " + orderCreatedEvent.getOrderId());
        // Additional logic to mark payment as successful
    }
    
	@Override
    public void processPayment2(OrderCreatedEvent event) {
		 System.out.println("Processing payment for order: " + event.getOrderId());

	        // Payment logic here (mock payment for now)
	        double amount = 100.00; // Assume a fixed amount for testing
	        boolean paymentSuccess = true;

	        if (paymentSuccess) {
	            System.out.println("Payment successful for order: " + event.getOrderId());

	            // Create and send PaymentSuccessEvent
	            PaymentSuccessEvent paymentSuccessEvent = new PaymentSuccessEvent(
	                event.getOrderId(),
	                "20",
	                amount,
	                "SUCCESS"
	            );

	            rabbitTemplate.convertAndSend("payment.exchange", "payment.success", paymentSuccessEvent);
	        } else {
	            System.out.println("Payment failed for order: " + event.getOrderId());
	        }
    }
}
