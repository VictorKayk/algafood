package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.UserCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.UserUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.UserResponseDTO;
import com.victorkayk.algafood.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toResponseDTO(User entity);

    User toEntity(UserResponseDTO dto);

    User createRequestDTOToEntity(UserCreateRequestDTO dto);

    UserCreateRequestDTO toCreateRequestDTO(User entity);

    User updateRequestDTOToEntity(UserUpdateRequestDTO dto);

    UserUpdateRequestDTO toUpdateRequestDTO(User entity);
}
