package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.PaymentMethodCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.PaymentMethodUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.PaymentMethodResponseDTO;
import com.victorkayk.algafood.domain.model.PaymentMethod;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {
    List<PaymentMethodResponseDTO> toResponseDTO(List<PaymentMethod> entity);

    List<PaymentMethodResponseDTO> toResponseDTO(Set<PaymentMethod> entity);

    PaymentMethodResponseDTO toResponseDTO(PaymentMethod entity);

    PaymentMethod toEntity(PaymentMethodResponseDTO dto);

    PaymentMethod createRequestDTOToEntity(PaymentMethodCreateRequestDTO dto);

    PaymentMethodCreateRequestDTO entityToCreateRequestDTO(PaymentMethod entity);

    PaymentMethod updateRequestDTOToEntity(PaymentMethodUpdateRequestDTO dto);

    PaymentMethodUpdateRequestDTO entityToUpdateRequestDTO(PaymentMethod entity);
}
