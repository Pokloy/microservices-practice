package com.example.demo.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.ProductClient;
import com.example.demo.controller.UserClient;
//import com.example.demo.controller.fallback.UserClientFallback;
import com.example.demo.model.dao.OrderRepository;
import com.example.demo.model.dao.entity.OrderEntity;
import com.example.demo.model.dao.entity.ProductEntity;
import com.example.demo.model.dto.UserResponse;
import com.example.demo.model.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrderServiceImpl extends OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductClient productClient;

    @Autowired
    UserClient userClient;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderEntity placeOrder(OrderEntity order) {
        return orderRepository.save(order);
    }

    public ProductEntity fetchProductDetails(Long productId) {
        return productClient.getProductById(productId);
    }

    public OrderEntity fetchOrderDetails(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId).orElse(new OrderEntity());

        // Fetch user details to set customer name
        UserResponse user = userClient.getUserById(order.getUserId());
        order.setCustomerName(user.getName()); 

        return order;
    }
}
