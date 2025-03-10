package com.example.demo.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.example.demo.controller.ProductClient;
import com.example.demo.controller.UserClient;
import com.example.demo.model.dao.OrderRepository;
import com.example.demo.model.dao.entity.OrderEntity;
import com.example.demo.model.dao.entity.ProductEntity;
import com.example.demo.model.dto.ProductDTO;
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
        // Validate user by calling User Service
        UserResponse user = userClient.getUserById(order.getUserId());

        if (user == null) {
            throw new RuntimeException("User not found with ID: " + order.getUserId());
        }

        // Proceed with order placement
        order.setCustomerName(user.getName()); // Set customer's name from User Service
        return orderRepository.save(order);
    }
    
    public ProductEntity fetchProductDetails(Long productId) {
        return productClient.getProductById(productId);
    }
    
    public OrderEntity fetchOrderDetails(Long productId) {
    	return orderRepository.findById(productId).orElse(new OrderEntity());
    }
    
    @Override
    @CircuitBreaker(name = "userServiceBreaker", fallbackMethod = "fallbackGetUser")
    public UserResponse getUser(Long userId) {
        return userClient.getUserById(userId); // Call USER-SERVICE
    }
    
    // Fallback Method (Executes when USER-SERVICE is down)
    public UserResponse userFallback(Long userId, Throwable t) {
        return new UserResponse(userId, "Fallback User", "fallback@example.com");
    }
}
