package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record RestaurantCreateRequestDTO(
        @NotBlank
        String name,

        @NotNull
        @Positive
        BigDecimal shippingFee,

        @Valid
        @NotNull
        KitchenIdRequestDTO kitchen,

        @Valid
        @NotNull
        AddressRequest address
) {
}
