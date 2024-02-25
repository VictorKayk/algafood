package com.victorkayk.algafood.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public record OrderResponseDTO(
        String id,
        BigDecimal subtotal,
        BigDecimal shippingFee,
        BigDecimal total,
        String status,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
        OffsetDateTime createdAt,
        AddressResponseDTO address,
        PaymentMethodResponseDTO paymentMethod,
        UserResponseDTO client,
        RestaurantSimplifiedResponseDTO restaurant,
        List<OrderItemResponseDTO> items
) {
}
