package com.victorkayk.algafood.api.dto.response;

public record AddressResponseDTO(
        String zipCode,
        String publicPlace,
        String number,
        String complement,
        String neighborhood,
        CitySimplifiedResponseDTO city
) {
}
