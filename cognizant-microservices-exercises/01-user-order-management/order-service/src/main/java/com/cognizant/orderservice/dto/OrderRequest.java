package com.cognizant.orderservice.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Long userId;
    private String productName;
    private Integer quantity;
    private Double totalPrice;
}
