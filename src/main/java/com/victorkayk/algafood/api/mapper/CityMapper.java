package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.CityRequestDTO;
import com.victorkayk.algafood.api.dto.response.CityResponseDTO;
import com.victorkayk.algafood.domain.model.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityResponseDTO toResponseDTO(City entity);

    City toEntity(CityResponseDTO dto);

    City requestDTOToEntity(CityRequestDTO dto);
}
