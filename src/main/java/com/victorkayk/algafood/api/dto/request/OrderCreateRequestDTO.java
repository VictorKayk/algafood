package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderCreateRequestDTO(
        @NotNull
        @Valid
        AddressRequest address,

        @NotNull
        @Valid
        IdRequestDTO paymentMethod,

        @NotNull
        @Valid
        IdRequestDTO client,

        @NotNull
        @Valid
        IdRequestDTO restaurant,

        @NotNull
        @Valid
        List<OrderItemCreateRequestDTO> items
) {
}
