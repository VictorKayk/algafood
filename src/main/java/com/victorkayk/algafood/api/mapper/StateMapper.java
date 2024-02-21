package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.StatePostRequestDTO;
import com.victorkayk.algafood.api.dto.request.StatePutRequestDTO;
import com.victorkayk.algafood.api.dto.response.StateResponseDTO;
import com.victorkayk.algafood.domain.model.State;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StateMapper {
    StateResponseDTO toResponseDTO(State entity);

    State toEntity(StateResponseDTO dto);

    State postRequestDTOToEntity(StatePostRequestDTO dto);

    State putRequestDTOToEntity(StatePutRequestDTO dto);
}
