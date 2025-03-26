package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dao.entity.OrderEntity;
import com.example.demo.model.dto.UserResponse;
import com.example.demo.model.service.OrderEventPublisher;
import com.example.demo.model.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
    private OrderService orderService;
	
	@Autowired
	private UserClient userClient;
	
    @Autowired
    private OrderEventPublisher eventPublisher;
    
    @GetMapping("/status")
    public String getStatus() {
        return "Order Service is running...";
    }
    
    @GetMapping
    public String getTest() {
        return "Order Service Test 1...";
    }
    
    @PostMapping("/place")
    public ResponseEntity<OrderEntity> placeOrder(@RequestBody OrderEntity order) {
        OrderEntity savedOrder = orderService.placeOrder(order);
        return ResponseEntity.ok(savedOrder);
    }
    
    @GetMapping("/{id}")
    public OrderEntity getOrderById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
    	return orderService.fetchOrderDetails(token,id);
    }
    
    @GetMapping("/users/{id}")
    public UserResponse getUserById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        return userClient.getUserById(token,id);
    }
    
    @PostMapping("/test")
    public String placeOrder(@RequestParam String orderDetails) {
        eventPublisher.sendOrderEvent(orderDetails);
        return "Order placed: " + orderDetails;
    }
}