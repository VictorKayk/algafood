package com.victorkayk.algafood.api.dto.request;

public record CityRequestDTO(
        String name,
        StateRequestDTO state
) {
}
