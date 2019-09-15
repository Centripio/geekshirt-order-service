package com.geekshirt.orderservice.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Order {
    private String orderId;
    private String status;
    private String accountId;
    private Double totalAmount;
    private Double totalTax;
    private Date transactionDate;
}
