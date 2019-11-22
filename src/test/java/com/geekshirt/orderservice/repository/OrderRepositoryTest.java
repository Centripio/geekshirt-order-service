package com.geekshirt.orderservice.repository;

import com.geekshirt.orderservice.entities.Order;
import com.geekshirt.orderservice.repositories.OrderRepository;
import com.geekshirt.orderservice.util.OrderStatus;
import com.geekshirt.orderservice.util.RepositoryDataUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void setup() {
        orderRepository.save(RepositoryDataUtils.getMockNotPersistentOrder("9876512", OrderStatus.DELIVERED));
        orderRepository.save(RepositoryDataUtils.getMockNotPersistentOrder("9876512", OrderStatus.DELIVERED));
        orderRepository.save(RepositoryDataUtils.getMockNotPersistentOrder("9876512", OrderStatus.DELIVERED));
        orderRepository.save(RepositoryDataUtils.getMockNotPersistentOrder("9876999", OrderStatus.DELIVERED));
        orderRepository.save(RepositoryDataUtils.getMockNotPersistentOrder("9876999", OrderStatus.SHIPPED));
    }

    @Test
    public void shouldSaveOrderWhenSaveIsCalled() {
        Order order = RepositoryDataUtils.getMockNotPersistentOrder("9876512", OrderStatus.PENDING);

        Order newOrder = orderRepository.save(order);

        Assertions.assertNotNull(newOrder.getId());

        Order foundOrder = orderRepository.findOrderByOrderId(newOrder.getOrderId());

        Assertions.assertNotNull(foundOrder);
        Assertions.assertEquals(order.getAccountId(), foundOrder.getAccountId());
        Assertions.assertNotNull(foundOrder.getOrderId());
        Assertions.assertEquals(newOrder.getOrderId(), foundOrder.getOrderId());
        Assertions.assertNotNull(foundOrder.getPaymentStatus());
        Assertions.assertNotNull(foundOrder.getStatus());
        Assertions.assertNotNull(foundOrder.getTotalAmount());
        Assertions.assertNotNull(foundOrder.getTotalTax());
        Assertions.assertNotNull(foundOrder.getTotalAmountTax());
        Assertions.assertNotNull(foundOrder.getTransactionDate());
    }

    @Test
    public void shouldReturnAllOrdersWhenFindAllIsCalled() {
        List<Order> orders = orderRepository.findAll();
        Assertions.assertEquals(5, orders.size());
    }

    @Test
    public void shouldReturnAllOrdersByAccountIdWhenFindByAccountIdIsCalled() {
        List<Order> orders = orderRepository.findOrdersByAccountId("9876512");
        Assertions.assertEquals(3, orders.size());
    }

    @Test
    public void shouldReturnOrderWhenFindByOrderIdIsCalled() {
        List<Order> orders = orderRepository.findOrdersByAccountId("9876512");
        Order order = orders.get(0);

        Order orderById = orderRepository.findOrderByOrderId(order.getOrderId());
        Assertions.assertNotNull(orderById);
        Assertions.assertEquals(order.getOrderId(), orderById.getOrderId());
    }


}
