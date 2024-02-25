package com.victorkayk.algafood.api.dto.request;

import com.victorkayk.algafood.domain.enums.StatusOrderEnum;
import jakarta.validation.constraints.NotNull;

public record OrderUpdateStatusRequestDTO(
        @NotNull
        StatusOrderEnum status
) {
}
