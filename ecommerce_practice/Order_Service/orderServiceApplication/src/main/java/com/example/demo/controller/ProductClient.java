package com.example.demo.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.dao.entity.ProductEntity;

@FeignClient(name = "PRODUCT-SERVICE", path = "/products")
public interface ProductClient {
    @GetMapping("/{productId}")
    ProductEntity getProductById(@PathVariable Long productId);
}
