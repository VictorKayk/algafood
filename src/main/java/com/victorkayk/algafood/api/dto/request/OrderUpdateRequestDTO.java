package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderUpdateRequestDTO(
        @Valid
        AddressRequest address,

        @Valid
        IdRequestDTO paymentMethod,

        @Valid
        IdRequestDTO client,

        @Valid
        IdRequestDTO restaurant,

        @Valid
        List<OrderItemCreateRequestDTO> items
) {
}
