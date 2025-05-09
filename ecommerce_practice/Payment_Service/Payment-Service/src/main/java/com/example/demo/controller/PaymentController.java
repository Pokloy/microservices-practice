package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.event.OrderCreatedEvent;
import com.example.demo.model.dto.PaymentSuccessEvent;
import com.example.demo.model.service.PaymentService;


@RestController
@RequestMapping("/payment")
public class PaymentController {
	
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process-payment")
    public String processPayment(@RequestBody PaymentSuccessEvent request) {
        // Simulate payment processing logic
        System.out.println("Processing payment for order ID: " + request.getOrderId());

        // Publish a PaymentSuccessEvent to RabbitMQ
        PaymentSuccessEvent event = new PaymentSuccessEvent(request.getOrderId());
        rabbitTemplate.convertAndSend("payment.exchange", "payment.success", event);

        return "Payment processed successfully for order ID: " + request.getOrderId();
    }
	
    @GetMapping("/test")
    public String getProductById() {
        return "testing payment service";
    }
    
    @PostMapping("/processing-payment-2")
    public String processPayment2(@RequestBody OrderCreatedEvent event) {

    	paymentService.processPayment2(event);
    	return "Payment Process Version 2 processed";
    }

}
