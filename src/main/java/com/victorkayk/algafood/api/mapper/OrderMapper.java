package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.OrderCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.OrderUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.OrderResponseDTO;
import com.victorkayk.algafood.domain.model.Order;
import com.victorkayk.algafood.domain.model.State;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "address.city.state.name", target = "address.city.state")
    @Mapping(source = "subTotal", target = "subtotal")
    OrderResponseDTO toResponseDTO(Order entity);

    Order toEntity(OrderResponseDTO dto);

    Order createRequestDTOToEntity(OrderCreateRequestDTO dto);

    OrderCreateRequestDTO entityToCreateRequestDTO(Order entity);

    Order updateRequestDTOToEntity(OrderUpdateRequestDTO dto);

    OrderUpdateRequestDTO entityToUpdateRequestDTO(Order entity);

    default State map(String value) {
        if (value == null) {
            return null;
        }
        State state = new State();
        state.setName(value);
        return state;
    }
}
