package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.Valid;

public record CityUpdateRequestDTO(
        String name,

        @Valid
        StateIdRequestDTO state
) {
}
