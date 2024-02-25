package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.GroupCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.GroupUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.GroupResponseDTO;
import com.victorkayk.algafood.domain.model.Group;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    List<GroupResponseDTO> toResponseDTO(List<Group> entity);

    List<GroupResponseDTO> toResponseDTO(Set<Group> entity);

    GroupResponseDTO toResponseDTO(Group entity);

    Group toEntity(GroupResponseDTO dto);

    Group createRequestDTOToEntity(GroupCreateRequestDTO dto);

    GroupCreateRequestDTO entityToCreateRequestDTO(Group entity);

    Group updateRequestDTOToEntity(GroupUpdateRequestDTO dto);

    GroupUpdateRequestDTO entityToUpdateRequestDTO(Group entity);
}
