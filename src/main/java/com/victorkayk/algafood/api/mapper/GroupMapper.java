package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.GroupRequestDTO;
import com.victorkayk.algafood.api.dto.response.GroupResponseDTO;
import com.victorkayk.algafood.domain.model.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupResponseDTO toResponseDTO(Group entity);

    Group toEntity(GroupResponseDTO dto);

    Group requestDTOToEntity(GroupRequestDTO dto);
}
