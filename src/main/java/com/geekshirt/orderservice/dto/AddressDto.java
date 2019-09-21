package com.geekshirt.orderservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressDto {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
