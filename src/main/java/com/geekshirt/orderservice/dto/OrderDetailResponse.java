package com.geekshirt.orderservice.dto;

import lombok.Data;

@Data
public class OrderDetailResponse {
    private long id;
    private int quantity;
    private double price;
    private double tax;
    private String upc;
    private Double totalAmount;
}
