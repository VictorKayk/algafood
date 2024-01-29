package com.victorkayk.algafood.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusOrderEnum {
    CREATED("created"),
    CONFIRMED("confirmed"),
    DELIVERED("delivered"),
    CANCELED("canceled");

    private final String value;
}
