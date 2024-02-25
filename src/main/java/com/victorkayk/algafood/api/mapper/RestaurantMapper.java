package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.RestaurantCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.RestaurantUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.RestaurantResponseDTO;
import com.victorkayk.algafood.domain.model.Restaurant;
import com.victorkayk.algafood.domain.model.State;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    @Mapping(source = "address.city.state.name", target = "address.city.state")
    List<RestaurantResponseDTO> toResponseDTO(List<Restaurant> entity);

    @Mapping(source = "address.city.state.name", target = "address.city.state")
    RestaurantResponseDTO toResponseDTO(Restaurant entity);

    Restaurant toEntity(RestaurantResponseDTO dto);

    Restaurant createRequestDTOToEntity(RestaurantCreateRequestDTO dto);

    RestaurantCreateRequestDTO entityToCreateRequestDTO(Restaurant entity);

    Restaurant updateRequestDTOToEntity(RestaurantUpdateRequestDTO dto);

    RestaurantUpdateRequestDTO entityToUpdateRequestDTO(Restaurant entity);

    default State map(String value) {
        if (value == null) {
            return null;
        }
        State state = new State();
        state.setName(value);
        return state;
    }
}
