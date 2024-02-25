package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record OrderUpdateRequestDTO(
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
        @Size(min = 1)
        List<OrderItemCreateRequestDTO> items
) {
}
