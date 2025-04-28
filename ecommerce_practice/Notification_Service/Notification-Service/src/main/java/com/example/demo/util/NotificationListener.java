package com.example.demo.util;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.event.PaymentSuccessEvent;

@Component
public class NotificationListener {

    @RabbitListener(queues = "payment.queue")
    public void handlePaymentSuccess(PaymentSuccessEvent event) {
        System.out.println("📧 Sending notification to user: " + event.getUserId());
        System.out.println("✅ Order ID: " + event.getOrderId() + " paid successfully.");
        // You can extend this to actually send an email or integrate Twilio/SMS here
    }
}

