package com.example.demo.model.service;

import com.example.demo.model.dao.entity.PaymentEntity;
import com.example.demo.model.dto.OrderCreatedEvent;

public abstract class PaymentService {
	public abstract PaymentEntity processPayment(OrderCreatedEvent orderEvent);
}
