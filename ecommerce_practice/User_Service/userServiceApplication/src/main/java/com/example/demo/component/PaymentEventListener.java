package com.example.demo.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.PaymentSuccessEvent;
import com.example.demo.model.service.UserService;

@Component
public class PaymentEventListener {

    @Autowired
    private UserService userService;

    
    @RabbitListener(queues = "payment.queue")
    public void handlePaymentSuccessEvent(PaymentSuccessEvent event) {
        System.out.println("Received payment success event: " + event.getOrderId());
        
        // Process order finalization in UserService
        userService.finalizeOrder(event);
    }
    
    
//    @RabbitListener(queues = "paymentSuccessQueue")
//    public void handlePaymentSuccess(PaymentSuccessEvent event) {
//        System.out.println("User Service received payment event: " + event);
//        
//        if (event.getAmount() > 500) { // Simulate failure for high payments
//            throw new RuntimeException("Simulated error to test DLQ");
//        }
//
//        // Process normally if not failing
//        userService.finalizeOrder(event);
//    }

    
}
