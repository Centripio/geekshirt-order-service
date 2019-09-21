package com.geekshirt.orderservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreditCardDto {
    private Long id;
    private String nameOnCard;
    private String number;
    private String type;
    private String expirationMonth;
    private String expirationYear;
    private String ccv;
}
