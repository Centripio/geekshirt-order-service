package com.geekshirt.orderservice.util;

import com.geekshirt.orderservice.dto.OrderRequest;
import com.geekshirt.orderservice.exception.IncorrectOrderRequestException;

public class OrderValidator {
    public static boolean validateOrder(OrderRequest order) {
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new IncorrectOrderRequestException(ExceptionMessagesEnum.INCORRECT_REQUEST_EMPTY_ITEMS_ORDER.getValue());
        }
        return true;
    }
}
