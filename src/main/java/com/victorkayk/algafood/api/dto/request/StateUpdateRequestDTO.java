package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record StateUpdateRequestDTO(
        @NotBlank
        String name
) {
}
