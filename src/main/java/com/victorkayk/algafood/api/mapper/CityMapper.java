package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.CityCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.CityUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.CityResponseDTO;
import com.victorkayk.algafood.domain.model.City;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {
    List<CityResponseDTO> toResponseDTO(List<City> entity);

    CityResponseDTO toResponseDTO(City entity);

    City toEntity(CityResponseDTO dto);

    City createRequestDTOToEntity(CityCreateRequestDTO dto);

    CityCreateRequestDTO entityToCreateRequestDTO(City entity);

    City updateRequestDTOToEntity(CityUpdateRequestDTO dto);

    CityUpdateRequestDTO entityToUpdateRequestDTO(City entity);
}
