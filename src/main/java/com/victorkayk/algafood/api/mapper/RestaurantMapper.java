package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.RestaurantRequestDTO;
import com.victorkayk.algafood.api.dto.response.RestaurantResponseDTO;
import com.victorkayk.algafood.domain.model.Restaurant;
import com.victorkayk.algafood.domain.model.State;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    @Mapping(source = "address.city.state.name", target = "address.city.state")
    RestaurantResponseDTO toResponseDTO(Restaurant entity);

    Restaurant toEntity(RestaurantResponseDTO dto);

    Restaurant requestDTOToEntity(RestaurantRequestDTO dto);

    default State map(String value) {
        if (value == null) {
            return null;
        }
        State state = new State();
        state.setName(value);
        return state;
    }
}
