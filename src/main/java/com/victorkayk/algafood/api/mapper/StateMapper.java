package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.StateRequestDTO;
import com.victorkayk.algafood.api.dto.response.StateResponseDTO;
import com.victorkayk.algafood.domain.model.State;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StateMapper {
    StateResponseDTO toResponseDTO(State entity);

    State toEntity(StateResponseDTO dto);

    State requestDTOToEntity(StateRequestDTO dto);
}
