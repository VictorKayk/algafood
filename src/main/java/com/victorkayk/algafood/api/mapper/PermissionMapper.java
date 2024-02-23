package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.PermissionCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.PermissionUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.PermissionResponseDTO;
import com.victorkayk.algafood.domain.model.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionResponseDTO toResponseDTO(Permission entity);

    Permission toEntity(PermissionResponseDTO dto);

    Permission createRequestDTOToEntity(PermissionCreateRequestDTO dto);

    PermissionCreateRequestDTO entityToCreateRequestDTO(Permission entity);

    Permission updateRequestDTOToEntity(PermissionUpdateRequestDTO dto);

    PermissionUpdateRequestDTO entityToUpdateRequestDTO(Permission entity);
}
