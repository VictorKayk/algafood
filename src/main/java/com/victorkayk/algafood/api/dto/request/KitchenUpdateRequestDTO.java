package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record KitchenUpdateRequestDTO(
        @NotBlank
        String name
) {
}
