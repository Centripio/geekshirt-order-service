package com.geekshirt.orderservice.controllers;

import com.geekshirt.orderservice.dto.OrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class OrderController {

    @GetMapping(value = "order")
    public ResponseEntity<List<OrderResponse>> findAll() {
        List<OrderResponse> orderList = new ArrayList();

        OrderResponse response = new OrderResponse();
        response.setAccountId("999819");
        response.setOrderId("11123");
        response.setStatus("PENDING");
        response.setTotalAmount(100.00);
        response.setTotalTax(10.00);
        response.setTransactionDate(new Date());

        OrderResponse response02 = new OrderResponse();
        response02.setAccountId("999819");
        response02.setOrderId("11124");
        response02.setStatus("PENDING");
        response02.setTotalAmount(120.00);
        response02.setTotalTax(12.00);
        response02.setTransactionDate(new Date());

        orderList.add(response);
        orderList.add(response02);

        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @GetMapping(value = "order/{orderId}")
    public ResponseEntity<OrderResponse> findById(@PathVariable String orderId) {
        OrderResponse response = new OrderResponse();
        response.setAccountId("999819");
        response.setOrderId(orderId);
        response.setStatus("PENDING");
        response.setTotalAmount(100.00);
        response.setTotalTax(10.00);
        response.setTransactionDate(new Date());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "order/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest payload) {
        OrderResponse response = new OrderResponse();
        response.setAccountId(payload.getAccountId());
        response.setOrderId("9999");
        response.setStatus("PENDING");
        response.setTotalAmount(100.00);
        response.setTotalTax(10.00);
        response.setTransactionDate(new Date());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
