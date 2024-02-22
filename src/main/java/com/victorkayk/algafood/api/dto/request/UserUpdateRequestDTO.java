package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

public record UserUpdateRequestDTO(
        @Min(value = 3, message = "Name must have at least 3 characters")
        String name,

        @Email
        String email
) {
}
