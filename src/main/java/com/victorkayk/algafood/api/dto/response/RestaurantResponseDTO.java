package com.victorkayk.algafood.api.dto.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record RestaurantResponseDTO(
        Long id,
        String name,
        BigDecimal shippingFee,
        Boolean isActive,
        Boolean isOpen,
        KitchenResponseDTO kitchen,
        OffsetDateTime createdAt,
        AddressResponseDTO address
) {
}
