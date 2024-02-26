package com.victorkayk.algafood.domain.dto;

import com.victorkayk.algafood.domain.enums.StatusOrderEnum;

import java.time.LocalDate;

public record OrderFilterDTO(
        Long restaurantId,
        Long clientId,
        StatusOrderEnum status,
        LocalDate startDate,
        LocalDate endDate
) {
}
