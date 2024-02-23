package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.request.ProductCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.ProductUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.ProductResponseDTO;
import com.victorkayk.algafood.api.mapper.ProductMapper;
import com.victorkayk.algafood.domain.model.Product;
import com.victorkayk.algafood.domain.model.Restaurant;
import com.victorkayk.algafood.domain.service.ProductService;
import com.victorkayk.algafood.domain.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products")
public class RestaurantProductController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> list(@PathVariable Long restaurantId) {
        restaurantService.findById(restaurantId);

        return ResponseEntity.ok(
                productService.findAll(restaurantId).stream().map(productMapper::toResponseDTO).toList()
        );
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> find(@PathVariable Long restaurantId, @PathVariable Long productId) {
        ProductResponseDTO productResponseDTO = productMapper.toResponseDTO(productService.findById(restaurantId, productId));
        return ResponseEntity.ok(productResponseDTO);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> save(@PathVariable Long restaurantId, @RequestBody ProductCreateRequestDTO dto) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        Product product = productMapper.createRequestDTOToEntity(dto);
        product.setRestaurant(restaurant);
        return new ResponseEntity<>(productMapper.toResponseDTO(productService.save(product)), HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long restaurantId, @PathVariable Long productId, @RequestBody ProductUpdateRequestDTO dto) {
        Product product = productMapper.updateRequestDTOToEntity(dto);
        return ResponseEntity.ok(productMapper.toResponseDTO(productService.update(restaurantId, productId, product)));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable Long restaurantId, @PathVariable Long productId) {
        productService.delete(restaurantId, productId);
        return ResponseEntity.noContent().build();
    }
}
