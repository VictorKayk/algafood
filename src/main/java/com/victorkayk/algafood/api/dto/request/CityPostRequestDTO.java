package com.victorkayk.algafood.api.dto.request;

public record CityPostRequestDTO(
        String name,
        StatePostRequestDTO state
) {
}
