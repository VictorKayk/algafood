package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductUpdateRequestDTO(
        String name,
        String description,
        BigDecimal price,
        Boolean active,

        @Valid
        RestaurantIdRequestDTO restaurant
) {
}
