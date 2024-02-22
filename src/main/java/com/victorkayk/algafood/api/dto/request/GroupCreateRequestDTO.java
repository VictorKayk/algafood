package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record GroupCreateRequestDTO(
        @NotBlank
        String name
) {
}
