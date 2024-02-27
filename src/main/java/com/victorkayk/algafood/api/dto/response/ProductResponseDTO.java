package com.victorkayk.algafood.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Boolean isActive
) {
}
