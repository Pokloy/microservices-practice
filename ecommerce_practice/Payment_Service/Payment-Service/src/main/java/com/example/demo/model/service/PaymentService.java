package com.example.demo.model.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.event.OrderCreatedEvent;
import com.example.demo.model.dao.PaymentRepository;
import com.example.demo.model.dao.entity.PaymentEntity;
import com.example.demo.model.dto.PaymentSuccessEvent;

@Service
public class PaymentService {
	
	@Autowired
    RabbitTemplate rabbitTemplate;
    
    @Autowired
    PaymentRepository paymentRepository;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public PaymentService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void processPayment(OrderCreatedEvent orderId) {
    	PaymentEntity paymentE = new PaymentEntity();
        // Simulate payment processing
        System.out.println("✅ Payment processed successfully for Order ID: " + orderId.getOrderId());
        paymentE.setAmount(orderId.getAmount());
        paymentE.setOrderId(Long.parseLong(orderId.getOrderId()));
        paymentE.setStatus(orderId.getOrderId());
        paymentE.setUserId(Long.parseLong(orderId.getUserId()));
        paymentRepository.save(paymentE);
        rabbitTemplate.convertAndSend(exchange, routingKey, orderId);
        System.out.println("📡 PaymentSuccessEvent sent for Order ID: " + orderId.getOrderId());
    }
    
    public void processPayment2(OrderCreatedEvent event) {
    	PaymentEntity paymentE = new PaymentEntity();
        // Simulate payment processing
        System.out.println("✅ Payment processed successfully for Order ID: " + event.getOrderId());
        paymentE.setId(2L);
        paymentE.setAmount(event.getAmount());
        paymentE.setOrderId(Long.parseLong(event.getOrderId()));
        paymentE.setStatus(event.getStatus());
        paymentE.setUserId(Long.parseLong(event.getUserId()));
        paymentRepository.saveAndFlush(paymentE);

        // 🔔 Send event to RabbitMQ
        PaymentSuccessEvent successEvent = new PaymentSuccessEvent(
        		String.valueOf(paymentE.getOrderId())
        );

        rabbitTemplate.convertAndSend(
            "order.exchange",                  // Exchange
            "payment.success.routing.key",     // Routing Key
            successEvent                       // Message
        );

        System.out.println("📤 Sent PaymentSuccessEvent for orderId: " + event.getOrderId());
    }
}

