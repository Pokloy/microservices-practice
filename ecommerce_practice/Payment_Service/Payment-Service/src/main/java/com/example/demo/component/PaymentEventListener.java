package com.example.demo.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.event.OrderCreatedEvent;
import com.example.demo.model.service.PaymentService;

@Component
public class PaymentEventListener {

    @Autowired
    private PaymentService paymentService;
    
    @RabbitListener(queues = "payment.queue")
    public void passOrderToUser(OrderCreatedEvent event) {
        System.out.println("Sending to User Service order Id: " + event);
        paymentService.processPayment(event);
    }

}



