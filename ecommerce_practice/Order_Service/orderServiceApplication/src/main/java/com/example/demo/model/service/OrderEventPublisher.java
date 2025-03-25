package com.example.demo.model.service;

public abstract class OrderEventPublisher {
	public abstract void sendOrderEvent(String orderMessage);
}
