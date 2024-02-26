package com.victorkayk.algafood.api.dto.request;

import com.victorkayk.algafood.domain.enums.StatusOrderEnum;

import java.time.LocalDate;

public record OrderFilterRequestDTO(
        Long restaurantId,
        Long clientId,
        StatusOrderEnum status,
        LocalDate startDate,
        LocalDate endDate
) {
}
