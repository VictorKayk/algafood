package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CityCreateRequestDTO(
        @NotBlank
        String name,

        @Valid
        @NotNull
        IdRequestDTO state
) {
}
