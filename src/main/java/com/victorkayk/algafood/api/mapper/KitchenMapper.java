package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.KitchenRequestDTO;
import com.victorkayk.algafood.api.dto.response.KitchenResponseDTO;
import com.victorkayk.algafood.domain.model.Kitchen;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KitchenMapper {
    KitchenResponseDTO toResponseDTO(Kitchen entity);

    Kitchen toEntity(KitchenResponseDTO dto);

    Kitchen requestDTOToEntity(KitchenRequestDTO dto);
}
