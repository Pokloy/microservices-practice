package com.example.demo.model.dto;

import lombok.Data;

@Data
public class OrderRequest {
	private Long userId;
	private Long productId;
	private Integer quantity;
	private Double totalPrice;
}
