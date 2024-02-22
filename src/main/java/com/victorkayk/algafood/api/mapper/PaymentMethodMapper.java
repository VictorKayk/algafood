package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.PaymentMethodCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.PaymentMethodUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.PaymentMethodResponseDTO;
import com.victorkayk.algafood.domain.model.PaymentMethod;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {
    PaymentMethodResponseDTO toResponseDTO(PaymentMethod entity);

    PaymentMethod toEntity(PaymentMethodResponseDTO dto);

    PaymentMethod createRequestDTOToEntity(PaymentMethodCreateRequestDTO dto);

    PaymentMethodCreateRequestDTO entityToCreateRequestDTO(PaymentMethod entity);

    PaymentMethod updateRequestDTOToEntity(PaymentMethodUpdateRequestDTO dto);

    PaymentMethodUpdateRequestDTO entityToUpdateRequestDTO(PaymentMethod entity);
}
