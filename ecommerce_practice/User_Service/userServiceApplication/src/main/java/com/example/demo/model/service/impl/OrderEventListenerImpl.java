package com.example.demo.model.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.demo.model.service.OrderEventListener;

@Service
public class OrderEventListenerImpl extends OrderEventListener {
    @RabbitListener(queues = "orderQueue")
    public void receiveOrderMessage(String orderMessage) {
        System.out.println("Received Order Event in User Service: " + orderMessage);
    }
}
