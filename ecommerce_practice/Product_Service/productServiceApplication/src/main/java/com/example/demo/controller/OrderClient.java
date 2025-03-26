package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.cloud.openfeign.FeignClient;

import com.example.demo.model.dto.OrderResponse;

@FeignClient(name = "order-service")
public interface OrderClient {
	@GetMapping("/order/{id}")
    OrderResponse getOrderById(@RequestHeader("Authorization") String token, @PathVariable Long id);
}
