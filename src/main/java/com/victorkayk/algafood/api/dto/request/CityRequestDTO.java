package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CityRequestDTO(
        @NotBlank
        String name,

        @Valid
        @NotNull
        StateRequestDTO state
) {
}
