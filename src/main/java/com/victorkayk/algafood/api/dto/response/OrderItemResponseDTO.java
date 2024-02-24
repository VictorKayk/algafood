package com.victorkayk.algafood.api.dto.response;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
        Long id,
        BigDecimal unitPrice,
        Integer quantity,
        BigDecimal totalPrice,
        String note,
        ProductResponseDTO product
) {
}
