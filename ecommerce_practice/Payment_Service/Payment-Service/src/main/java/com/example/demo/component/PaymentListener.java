package com.example.demo.component;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dao.entity.PaymentEntity;
import com.example.demo.model.dto.OrderCreatedEvent;
import com.example.demo.model.service.PaymentService;

@Component
public class PaymentListener {
    @Autowired
	PaymentService paymentService;

    @RabbitListener(queues = "order.queue")
    public void handleOrderCreatedEvent(OrderCreatedEvent orderEvent) {
        PaymentEntity payment = paymentService.processPayment(orderEvent);
        System.out.println("Payment processed successfully for Order ID: " + payment.getOrderId());
    }
}

