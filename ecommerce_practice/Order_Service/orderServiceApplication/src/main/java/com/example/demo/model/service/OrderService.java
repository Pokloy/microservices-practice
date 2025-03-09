package com.example.demo.model.service;

import com.example.demo.model.dao.entity.OrderEntity;
import com.example.demo.model.dao.entity.ProductEntity;

public abstract class OrderService {
	public abstract OrderEntity placeOrder(OrderEntity order);
	public abstract ProductEntity fetchProductDetails(Long productId);
}
