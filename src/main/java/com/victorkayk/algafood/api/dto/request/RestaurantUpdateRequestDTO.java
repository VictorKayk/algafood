package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record RestaurantUpdateRequestDTO(
        String name,

        @Positive
        BigDecimal shippingFee,

        @Valid
        KitchenIdRequestDTO kitchen
) {
}
