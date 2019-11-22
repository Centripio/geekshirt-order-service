package com.geekshirt.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private String orderId;
    private String status;
    private String accountId;
    private Double totalAmount;
    private Double totalTax;
    private Double totalAmountTax;
    private Date transactionDate;

    List<OrderDetailResponse> details;
}
