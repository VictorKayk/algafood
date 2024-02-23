package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.Valid;

import java.math.BigDecimal;

public record ProductUpdateRequestDTO(
        String name,
        String description,
        BigDecimal price,
        Boolean active,

        @Valid
        IdRequestDTO restaurant
) {
}
