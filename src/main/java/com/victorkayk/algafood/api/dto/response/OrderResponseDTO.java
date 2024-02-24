package com.victorkayk.algafood.api.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponseDTO(
        Long id,
        BigDecimal subtotal,
        BigDecimal shippingFee,
        BigDecimal total,
        String status,
        AddressResponseDTO address,
        PaymentMethodResponseDTO paymentMethod,
        UserResponseDTO client,
        RestaurantSimplifiedResponseDTO restaurant,
        List<OrderItemResponseDTO> items
) {
}
