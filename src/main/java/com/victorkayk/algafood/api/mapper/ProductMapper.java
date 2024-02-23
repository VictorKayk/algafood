package com.victorkayk.algafood.api.mapper;

import com.victorkayk.algafood.api.dto.request.ProductCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.ProductUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.ProductResponseDTO;
import com.victorkayk.algafood.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDTO toResponseDTO(Product entity);

    Product toEntity(ProductResponseDTO dto);

    Product createRequestDTOToEntity(ProductCreateRequestDTO dto);

    ProductCreateRequestDTO entityToCreateRequestDTO(Product entity);

    Product updateRequestDTOToEntity(ProductUpdateRequestDTO dto);

    ProductUpdateRequestDTO entityToUpdateRequestDTO(Product entity);
}
