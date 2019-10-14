package com.geekshirt.orderservice.service;

import com.geekshirt.orderservice.client.CustomerServiceClient;
import com.geekshirt.orderservice.dao.JpaOrderDAO;
import com.geekshirt.orderservice.dto.AccountDto;
import com.geekshirt.orderservice.dto.OrderRequest;
import com.geekshirt.orderservice.entities.Order;
import com.geekshirt.orderservice.entities.OrderDetail;
import com.geekshirt.orderservice.exception.AccountNotFoundException;
import com.geekshirt.orderservice.exception.OrderNotFoundException;
import com.geekshirt.orderservice.util.Constants;
import com.geekshirt.orderservice.util.ExceptionMessagesEnum;
import com.geekshirt.orderservice.util.OrderStatus;
import com.geekshirt.orderservice.util.OrderValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private CustomerServiceClient customerClient;

    @Autowired
    private JpaOrderDAO orderDAO;

    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
        OrderValidator.validateOrder(orderRequest);

        AccountDto account = customerClient.findAccount(orderRequest.getAccountId())
                                        .orElseThrow(() -> new AccountNotFoundException(ExceptionMessagesEnum.ACCOUNT_NOT_FOUND.getValue()));
        Order newOrder = initOrder(orderRequest);
        return orderDAO.save(newOrder);
    }

    private Order initOrder(OrderRequest orderRequest) {
        Order orderObj = new Order();
        orderObj.setOrderId(UUID.randomUUID().toString());
        orderObj.setAccountId(orderRequest.getAccountId());
        orderObj.setStatus(OrderStatus.PENDING);

        List<OrderDetail> orderDetails = orderRequest.getItems().stream()
                .map(item -> OrderDetail.builder()
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .upc(item.getUpc())
                        .tax((item.getPrice() * item.getQuantity()) * Constants.TAX_IMPORT)
                        .order(orderObj).build())
                .collect(Collectors.toList());

        orderObj.setDetails(orderDetails);
        orderObj.setTotalAmount(orderDetails.stream().mapToDouble(OrderDetail::getPrice).sum());
        orderObj.setTotalTax(orderObj.getTotalAmount() * Constants.TAX_IMPORT);
        orderObj.setTransactionDate(new Date());

        return orderObj;
    }

    public List<Order> findAllOrders() {
        return orderDAO.findAll();
    }

    public Order findOrderById(String orderId) {
        return orderDAO.findByOrderId(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }

    public Order findById(long orderId) {
        return orderDAO.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }
}
