package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

public record OrderItemUpdateRequestDTO(
        @Positive
        Integer quantity,

        String note,

        @Valid
        IdRequestDTO product
) {
}
