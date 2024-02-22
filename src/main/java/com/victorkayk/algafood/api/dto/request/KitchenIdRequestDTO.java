package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.constraints.NotNull;

public record KitchenIdRequestDTO(
        @NotNull
        Long id
) {
}
