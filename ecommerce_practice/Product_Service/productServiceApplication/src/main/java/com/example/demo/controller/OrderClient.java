package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

import com.example.demo.model.dto.OrderResponse;

@FeignClient(name = "ORDER-SERVICE", path = "/order")
public interface OrderClient {
    @GetMapping("/{orderId}")
    OrderResponse getOrderById(@PathVariable Long orderId);
}
