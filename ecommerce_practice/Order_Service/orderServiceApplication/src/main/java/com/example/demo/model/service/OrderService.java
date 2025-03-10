package com.example.demo.model.service;

import com.example.demo.model.dao.entity.OrderEntity;
import com.example.demo.model.dao.entity.ProductEntity;
import com.example.demo.model.dto.UserResponse;

public abstract class OrderService {
	public abstract OrderEntity placeOrder(OrderEntity order);
	public abstract ProductEntity fetchProductDetails(Long productId);
	public abstract OrderEntity fetchOrderDetails(Long productId);
	public abstract UserResponse getUser(Long userId);
	public abstract UserResponse userFallback(Long userId, Throwable t);
}
