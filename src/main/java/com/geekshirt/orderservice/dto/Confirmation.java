package com.geekshirt.orderservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Confirmation {
    private String orderId;
    private String accountId;
    private String transactionId;
    private String transactionStatus;
    private String transactionAuthCode;
    private Date transactionDate;
}