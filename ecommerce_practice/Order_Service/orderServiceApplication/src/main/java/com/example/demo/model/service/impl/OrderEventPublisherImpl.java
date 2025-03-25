package com.example.demo.model.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.service.OrderEventPublisher;

@Service
public class OrderEventPublisherImpl extends OrderEventPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    public void sendOrderEvent(String orderMessage) {
        rabbitTemplate.convertAndSend("orderQueue", orderMessage);
        System.out.println("Sent Order Event: " + orderMessage);
    }
}
