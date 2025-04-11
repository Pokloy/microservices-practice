package com.example.demo.model.service;

import com.example.demo.model.dao.entity.OrderEntity;
import com.example.demo.model.dao.entity.ProductEntity;
import com.example.demo.model.dto.UserResponse;

public abstract class OrderService {
	public abstract OrderEntity placeOrder(OrderEntity order);
	public abstract ProductEntity fetchProductDetails(Long productId);
	public abstract OrderEntity fetchOrderDetails(String token,Long productId);
	public abstract void finalizeOrder(Long orderId);
}
