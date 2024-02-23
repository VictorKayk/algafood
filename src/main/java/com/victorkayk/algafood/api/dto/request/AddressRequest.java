package com.victorkayk.algafood.api.dto.request;

public record AddressRequest(
        String zipCode,
        String street,
        String number,
        String complement,
        String district,
        IdRequestDTO city
) {
}
