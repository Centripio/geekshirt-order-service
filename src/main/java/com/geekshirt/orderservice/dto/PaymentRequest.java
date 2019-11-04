package com.geekshirt.orderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {
    private String orderId;
    private String currency;
    private String accountId;
    private double amount;
    PaymentDetailsDto payment;
}