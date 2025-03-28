package com.example.demo.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.event.OrderCreatedEvent;
import com.example.demo.model.service.PaymentService;

@Component
public class PaymentEventListener {

    @Autowired
    PaymentService paymentService;

    // Listens to "order.queue" and processes incoming order events
    @RabbitListener(queues = "order.queue")
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        System.out.println("Received order event: " + event);

        // Processes the order event to handle payment
        paymentService.processPayment1(event);
    }
}



