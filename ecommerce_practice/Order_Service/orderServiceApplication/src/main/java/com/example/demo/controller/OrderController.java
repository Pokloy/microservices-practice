package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

import com.example.demo.model.dao.OrderRepository;
import com.example.demo.model.dao.entity.OrderEntity;
import com.example.demo.model.dto.OrderCreatedEvent;
import com.example.demo.model.dto.PaymentSuccessEvent;
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
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Autowired
    private OrderRepository orderRepository;
    
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
    
    @PostMapping("/create-order")
    public String createOrder(@RequestBody PaymentSuccessEvent event) {
        // Send the order event to RabbitMQ
    	System.out.println("Order Service Takes effect on URL '/create-order' ");
    	LocalDateTime now = LocalDateTime.now();
    	OrderEntity orderE = new OrderEntity();
    	orderE.setUserId(Long.parseLong(event.getUserId()));
    	orderE.setOrderDate(now);
    	orderE.setProductId((long)9);
    	orderE.setQuantity(1);
    	orderE.setStatus(event.getStatus());
    	orderE.setTotalPrice(event.getAmount());
    	orderRepository.save(orderE);
    	rabbitTemplate.convertAndSend("payment.exchange", "payment.success", event);
        return "Order created successfully!ssss";
    }
}