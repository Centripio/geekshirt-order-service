package com.geekshirt.orderservice.service;

import com.geekshirt.orderservice.client.CustomerServiceClient;
import com.geekshirt.orderservice.dto.AccountDto;
import com.geekshirt.orderservice.dto.OrderRequest;
import com.geekshirt.orderservice.entities.Order;
import com.geekshirt.orderservice.exception.AccountNotFoundException;
import com.geekshirt.orderservice.util.ExceptionMessagesEnum;
import com.geekshirt.orderservice.util.OrderValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private CustomerServiceClient customerClient;

    public Order createOrder(OrderRequest orderRequest) {
        OrderValidator.validateOrder(orderRequest);

        AccountDto account = customerClient.findAccount(orderRequest.getAccountId())
                                        .orElseThrow(() -> new AccountNotFoundException(ExceptionMessagesEnum.ACCOUNT_NOT_FOUND.getValue()));

        Order response = new Order();
        response.setAccountId(orderRequest.getAccountId());
        response.setOrderId("9999");
        response.setStatus("PENDING");
        response.setTotalAmount(100.00);
        response.setTotalTax(10.00);
        response.setTransactionDate(new Date());

        return response;
    }

    public List<Order> findAllOrders() {
        List<Order> orderList = new ArrayList();

        Order response = new Order();
        response.setAccountId("999819");
        response.setOrderId("11123");
        response.setStatus("PENDING");
        response.setTotalAmount(100.00);
        response.setTotalTax(10.00);
        response.setTransactionDate(new Date());

        Order response02 = new Order();
        response02.setAccountId("999819");
        response02.setOrderId("11124");
        response02.setStatus("PENDING");
        response02.setTotalAmount(120.00);
        response02.setTotalTax(12.00);
        response02.setTransactionDate(new Date());

        orderList.add(response);
        orderList.add(response02);

        return orderList;
    }

    public Order findOrderById(String orderId) {
        Order response = new Order();
        response.setAccountId("999819");
        response.setOrderId(orderId);
        response.setStatus("PENDING");
        response.setTotalAmount(100.00);
        response.setTotalTax(10.00);
        response.setTransactionDate(new Date());

        return response;
    }
}
