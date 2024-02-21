package com.victorkayk.algafood.api.dto.response;

public record CityResponseDTO(
        Long id,
        String name,
        StateResponseDTO state
) {
}
