package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.RestaurantPostRequestDTO;
import com.victorkayk.algafood.api.dto.request.RestaurantPutRequestDTO;
import com.victorkayk.algafood.api.dto.response.RestaurantResponseDTO;
import com.victorkayk.algafood.domain.model.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantResponseDTO toResponseDTO(Restaurant entity);

    Restaurant toEntity(RestaurantResponseDTO dto);

    Restaurant postRequestDTOToEntity(RestaurantPostRequestDTO dto);

    Restaurant putRequestDTOToEntity(RestaurantPutRequestDTO dto);
}
