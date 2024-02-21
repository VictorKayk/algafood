package com.victorkayk.algafood.api.dto.request;

public record CityPutRequestDTO(
        String name,
        StatePutRequestDTO state
) {
}
