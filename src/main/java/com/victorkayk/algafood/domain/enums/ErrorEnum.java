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
    RESTAURANT_NOT_FOUND("Restaurant not found", ErrorTypeEnum.NOT_FOUND);

    private final String title;
    private final ErrorTypeEnum status;
}
