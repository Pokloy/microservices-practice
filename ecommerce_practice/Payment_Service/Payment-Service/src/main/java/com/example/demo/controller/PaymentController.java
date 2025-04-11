package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.dto.PaymentSuccessEvent;


@RestController
@RequestMapping("/payment")
public class PaymentController {
	
    @Autowired
    private RabbitTemplate rabbitTemplate;

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
    
    

}
