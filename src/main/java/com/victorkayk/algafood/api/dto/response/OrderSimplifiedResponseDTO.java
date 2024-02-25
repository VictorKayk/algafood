package com.victorkayk.algafood.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record OrderSimplifiedResponseDTO(
        Long id,
        BigDecimal subtotal,
        BigDecimal shippingFee,
        BigDecimal total,
        String status,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
        OffsetDateTime createdAt,
        UserResponseDTO client,
        RestaurantSimplifiedResponseDTO restaurant
) {
}
