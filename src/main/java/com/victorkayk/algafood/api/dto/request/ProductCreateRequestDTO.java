package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductCreateRequestDTO(
        @NotBlank
        String name,

        @NotBlank
        String description,

        @NotNull
        @PositiveOrZero
        BigDecimal price,

        @NotNull
        @Valid
        RestaurantIdRequestDTO restaurant
) {
}
