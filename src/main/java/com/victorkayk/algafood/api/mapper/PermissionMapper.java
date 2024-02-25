package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.PermissionCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.PermissionUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.PermissionResponseDTO;
import com.victorkayk.algafood.domain.model.Permission;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    List<PermissionResponseDTO> toResponseDTO(Set<Permission> entity);

    List<PermissionResponseDTO> toResponseDTO(List<Permission> entity);

    PermissionResponseDTO toResponseDTO(Permission entity);

    Permission toEntity(PermissionResponseDTO dto);

    Permission createRequestDTOToEntity(PermissionCreateRequestDTO dto);

    PermissionCreateRequestDTO entityToCreateRequestDTO(Permission entity);

    Permission updateRequestDTOToEntity(PermissionUpdateRequestDTO dto);

    PermissionUpdateRequestDTO entityToUpdateRequestDTO(Permission entity);
}
