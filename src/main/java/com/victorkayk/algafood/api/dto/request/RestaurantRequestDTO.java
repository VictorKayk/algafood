package com.victorkayk.algafood.api.dto.request;

import com.victorkayk.algafood.api.dto.response.KitchenIdResponseDTO;

import java.math.BigDecimal;

public record RestaurantRequestDTO(
        String name,
        BigDecimal shippingFee,
        KitchenIdResponseDTO kitchen
) {
}
