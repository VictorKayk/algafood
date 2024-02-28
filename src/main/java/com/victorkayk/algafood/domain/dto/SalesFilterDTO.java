package com.victorkayk.algafood.domain.dto;

import com.victorkayk.algafood.domain.enums.PeriodEnum;

import java.time.LocalDate;

public record SalesFilterDTO(
        Long restaurantId,
        PeriodEnum period,
        LocalDate startDate,
        LocalDate endDate
) {
}
