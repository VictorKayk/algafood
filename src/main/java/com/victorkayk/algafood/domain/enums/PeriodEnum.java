package com.victorkayk.algafood.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PeriodEnum {
    DAY("day"),
    MONTH("month"),
    YEAR("year");

    private final String value;
}
