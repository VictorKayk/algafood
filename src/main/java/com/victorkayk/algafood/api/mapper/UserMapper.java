package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.UserCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.UserUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.UserResponseDTO;
import com.victorkayk.algafood.domain.model.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {
    List<UserResponseDTO> toResponseDTO(List<User> entity);

    List<UserResponseDTO> toResponseDTO(Set<User> entity);

    UserResponseDTO toResponseDTO(User entity);

    User toEntity(UserResponseDTO dto);

    User createRequestDTOToEntity(UserCreateRequestDTO dto);

    UserCreateRequestDTO toCreateRequestDTO(User entity);

    User updateRequestDTOToEntity(UserUpdateRequestDTO dto);

    UserUpdateRequestDTO toUpdateRequestDTO(User entity);
}
