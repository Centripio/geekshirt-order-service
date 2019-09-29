package com.geekshirt.orderservice.util;

public enum ExceptionMessagesEnum {
    ACCOUNT_NOT_FOUND("Account Not Found"),
    INCORRECT_REQUEST_EMPTY_ITEMS_ORDER("Empty Items are not allowed in the Order");

    ExceptionMessagesEnum(String msg) {
        value = msg;
    }

    private final String value;

    public String getValue(){
        return value;
    }
}
