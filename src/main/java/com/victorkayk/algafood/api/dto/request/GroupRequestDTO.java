package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record GroupRequestDTO(
        @NotBlank
        String name
) {
}
