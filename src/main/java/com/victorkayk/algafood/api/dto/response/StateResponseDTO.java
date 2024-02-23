package com.victorkayk.algafood.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StateResponseDTO(
        Long id,
        String name
) {
}
