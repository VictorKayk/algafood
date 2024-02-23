package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PermissionCreateRequestDTO(
        @NotBlank
        @Min(value = 3, message = "The name must be at least 3 characters")
        String name,

        @NotBlank
        @Min(value = 5, message = "The description must be at least 5 characters")
        String description
) {
}
