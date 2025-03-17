package com.example.demo.util;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventListener {

    @RabbitListener(queues = "orderQueue")
    public void receiveOrderMessage(String orderMessage) {
        System.out.println("Received Order Event in User Service: " + orderMessage);
    }
}

