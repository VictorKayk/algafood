package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UserPasswordRequestDTO(
        @NotBlank
        @Min(value = 3, message = "Password must have at least 3 characters")
        String password,

        @NotBlank
        @Min(value = 3, message = "New password must have at least 3 characters")
        String newPassword
) {
}
