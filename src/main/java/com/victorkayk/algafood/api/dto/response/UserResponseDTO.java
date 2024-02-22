package com.victorkayk.algafood.api.dto.response;

import java.time.OffsetDateTime;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        OffsetDateTime createdAt
) {
}
