package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dao.entity.OrderEntity;
import com.example.demo.model.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
    private OrderService orderService;
    
    @GetMapping("/status")
    public String getStatus() {
        return "Order Service is running...";
    }
    
    @GetMapping
    public String getTest() {
        return "Order Service Test 1...";
    }
    
    @PostMapping
    public ResponseEntity<OrderEntity> placeOrder(@RequestBody OrderEntity order) {
        OrderEntity savedOrder = orderService.placeOrder(order);
        return ResponseEntity.ok(savedOrder);
    }
    
    @GetMapping("/{id}")
    public OrderEntity getOrderById(@PathVariable Long id) {
    	return orderService.fetchOrderDetails(id);
    }
}