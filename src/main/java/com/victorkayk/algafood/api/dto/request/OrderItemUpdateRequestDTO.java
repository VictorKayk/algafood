package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderItemUpdateRequestDTO(
        @Positive
        Integer quantity,

        String note,

        @Valid
        IdRequestDTO product
) {
}
