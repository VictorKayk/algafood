package com.victorkayk.algafood.api.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record IdsRequestDTO (
    @NotNull
    List<Long> ids
) {}
