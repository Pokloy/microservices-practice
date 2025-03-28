package com.example.demo.model.service;

import com.example.demo.event.OrderCreatedEvent;

public abstract class PaymentService {
	public abstract void processPayment1(OrderCreatedEvent orderEvent);
	public abstract void processPayment2(OrderCreatedEvent event);
}
