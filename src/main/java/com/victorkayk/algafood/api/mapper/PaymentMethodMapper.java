package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.PaymentMethodRequestDTO;
import com.victorkayk.algafood.api.dto.response.PaymentMethodResponseDTO;
import com.victorkayk.algafood.domain.model.PaymentMethod;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {
    PaymentMethodResponseDTO toResponseDTO(PaymentMethod entity);

    PaymentMethod toEntity(PaymentMethodResponseDTO dto);

    PaymentMethod requestDTOToEntity(PaymentMethodRequestDTO dto);
}
