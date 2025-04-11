package com.example.demo.model.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.event.OrderCreatedEvent;
import com.example.demo.model.dto.PaymentSuccessEvent;

@Service
public class PaymentService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public PaymentService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void processPayment(OrderCreatedEvent orderId) {
        // Simulate payment processing
        System.out.println("✅ Payment processed successfully for Order ID: " + orderId.getOrderId());

        rabbitTemplate.convertAndSend(exchange, routingKey, orderId);
        System.out.println("📡 PaymentSuccessEvent sent for Order ID: " + orderId.getOrderId());
    }
}

