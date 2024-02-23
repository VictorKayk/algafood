package com.victorkayk.algafood.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AddressResponseDTO(
        String zipCode,
        String publicPlace,
        String number,
        String complement,
        String neighborhood,
        CitySimplifiedResponseDTO city
) {
}
