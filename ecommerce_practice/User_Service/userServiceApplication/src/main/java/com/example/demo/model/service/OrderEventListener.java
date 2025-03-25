package com.example.demo.model.service;

public abstract class OrderEventListener {
	public abstract void receiveOrderMessage(String orderMessage);
}
