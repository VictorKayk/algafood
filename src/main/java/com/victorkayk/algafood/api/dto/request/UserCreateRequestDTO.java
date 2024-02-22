package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UserCreateRequestDTO(
        @NotBlank
        @Min(value = 3, message = "Name must have at least 3 characters")
        String name,

        @Email
        @NotBlank
        String email,

        @NotBlank
        @Min(value = 3, message = "Password must have at least 3 characters")
        String password
) {
}
