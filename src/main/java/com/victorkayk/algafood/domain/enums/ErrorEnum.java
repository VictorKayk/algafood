package com.victorkayk.algafood.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorEnum {
    INVALID_DATA("Invalid data", ErrorTypeEnum.BAD_REQUEST),
    INTERNAL_ERROR("Internal error", ErrorTypeEnum.INTERNAL_ERROR),
    RESOURCE_NOT_FOUND("Resource not found", ErrorTypeEnum.NOT_FOUND),
    INVALID_PARAMETER("Invalid parameter", ErrorTypeEnum.BAD_REQUEST),
    MESSAGE_NOT_READABLE("Message not readable", ErrorTypeEnum.BAD_REQUEST),
    CITY_NOT_FOUND("City not found", ErrorTypeEnum.NOT_FOUND),
    KITCHEN_NOT_FOUND("Kitchen not found", ErrorTypeEnum.NOT_FOUND),
    STATE_NOT_FOUND("State not found", ErrorTypeEnum.NOT_FOUND),
    RESTAURANT_NOT_FOUND("Restaurant not found", ErrorTypeEnum.NOT_FOUND),
    STATE_IN_USE("State in use", ErrorTypeEnum.BAD_REQUEST),
    CITY_IN_USE("City in use", ErrorTypeEnum.BAD_REQUEST),
    KITCHEN_IN_USE("Kitchen in use", ErrorTypeEnum.BAD_REQUEST),
    RESTAURANT_IN_USE("Restaurant in use", ErrorTypeEnum.BAD_REQUEST),
    PAYMENT_METHOD_NOT_FOUND("Payment method not found", ErrorTypeEnum.NOT_FOUND),
    PAYMENT_METHOD_IN_USE("Payment method in use", ErrorTypeEnum.BAD_REQUEST),
    PAYMENT_METHOD_NOT_AVAILABLE("Payment method not available", ErrorTypeEnum.BAD_REQUEST),
    INVALID_PASSWORD("Invalid password", ErrorTypeEnum.BAD_REQUEST),
    PRODUCT_NOT_FOUND("Product not found", ErrorTypeEnum.NOT_FOUND),
    PRODUCT_IN_USE("Product in use", ErrorTypeEnum.BAD_REQUEST),
    PERMISSION_IN_USE("Permission in use", ErrorTypeEnum.BAD_REQUEST),
    PERMISSION_NOT_FOUND("Permission not found", ErrorTypeEnum.NOT_FOUND),
    ORDER_IN_USE("Order in use", ErrorTypeEnum.BAD_REQUEST),
    ORDER_NOT_FOUND("Order not found", ErrorTypeEnum.NOT_FOUND),
    NO_SUCH_METHOD("No such method", ErrorTypeEnum.BAD_REQUEST),
    USER_ALREADY_EXISTS("User already exists", ErrorTypeEnum.BAD_REQUEST);

    private final String title;
    private final ErrorTypeEnum status;
}
