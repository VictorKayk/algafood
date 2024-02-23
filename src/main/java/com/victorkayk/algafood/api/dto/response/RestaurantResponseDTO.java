package com.victorkayk.algafood.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RestaurantResponseDTO(
        Long id,
        String name,
        BigDecimal shippingFee,
        Boolean isActive,
        Boolean isOpen,
        KitchenResponseDTO kitchen,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
        OffsetDateTime createdAt,
        AddressResponseDTO address
) {
}
