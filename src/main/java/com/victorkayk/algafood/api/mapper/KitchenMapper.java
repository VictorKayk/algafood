package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.KitchenCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.KitchenUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.KitchenResponseDTO;
import com.victorkayk.algafood.domain.model.Kitchen;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KitchenMapper {
    List<KitchenResponseDTO> toResponseDTO(List<Kitchen> entity);

    KitchenResponseDTO toResponseDTO(Kitchen entity);

    Kitchen toEntity(KitchenResponseDTO dto);

    Kitchen createRequestDTOToEntity(KitchenCreateRequestDTO dto);

    KitchenCreateRequestDTO entityToCreateRequestDTO(Kitchen entity);

    Kitchen updateRequestDTOToEntity(KitchenUpdateRequestDTO dto);

    KitchenUpdateRequestDTO entityToUpdateRequestDTO(Kitchen entity);
}
