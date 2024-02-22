package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.StateCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.StateUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.StateResponseDTO;
import com.victorkayk.algafood.domain.model.State;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StateMapper {
    StateResponseDTO toResponseDTO(State entity);

    State toEntity(StateResponseDTO dto);

    State createRequestDTOToEntity(StateCreateRequestDTO dto);

    StateCreateRequestDTO entityToCreateRequestDTO(State entity);

    State updateRequestDTOToEntity(StateUpdateRequestDTO dto);

    StateUpdateRequestDTO entityToUpdateRequestDTO(State entity);
}
