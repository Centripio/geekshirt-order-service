package com.geekshirt.orderservice.util;

import com.geekshirt.orderservice.dto.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderServiceDataTestUtils {

    public static OrderRequest getMockOrderRequest(String accountId) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setAccountId(accountId);

        List<LineItem> items = new ArrayList<>();
        items.add(new LineItem("123456718285", 5, 1));
        items.add(new LineItem("123456718290", 10, 100));
        orderRequest.setItems(items);

        return orderRequest;
    }

    public static Confirmation getMockPayment(String accountId, OrderPaymentStatus paymentStatus) {
        Confirmation confirmation = new Confirmation();
        confirmation.setAccountId(accountId);
        confirmation.setTransactionDate(new Date());
        confirmation.setOrderId(UUID.randomUUID().toString());
        confirmation.setTransactionAuthCode(UUID.randomUUID().toString());
        confirmation.setTransactionId(UUID.randomUUID().toString());
        confirmation.setTransactionStatus(paymentStatus.name());
        return confirmation;
    }

    public static AccountDto getMockAccount(String accountId) {
        return AccountDto.builder()
                .address(getMockAddress())
                .creditCard(getMockCreditCard())
                .customer(getMockCustomerInfo())
                .status(AccountStatus.ACTIVE)
                .build();
    }

    private static AddressDto getMockAddress() {
        return AddressDto.builder()
                .street(" 1256 Kakachi Street")
                .city("Leaf Village")
                .state("Leaf State")
                .country("Leaf Nation")
                .zipCode("89134")
                .build();
    }

    private static CustomerDto getMockCustomerInfo() {
        return CustomerDto.builder()
                .firstName("Naruto")
                .lastName("Uzumaki")
                .email("carlos@centripio.io")
                .build();
    }

    private static CreditCardDto getMockCreditCard() {
        return CreditCardDto
                .builder()
                .nameOnCard("Naruto Uzumaki")
                .number("1234 5678 9123 9876")
                .type("VISA")
                .expirationMonth("03")
                .expirationYear("2022")
                .ccv("132")
                .build();
    }
}

