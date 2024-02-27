package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.request.ProductCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.ProductUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.ProductResponseDTO;
import com.victorkayk.algafood.api.mapper.ProductMapper;
import com.victorkayk.algafood.api.util.PageableUtils;
import com.victorkayk.algafood.domain.model.Product;
import com.victorkayk.algafood.domain.model.Restaurant;
import com.victorkayk.algafood.domain.service.ProductService;
import com.victorkayk.algafood.domain.service.RestaurantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Tag(name = "Restaurants", description = "Restaurant product endpoints")
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
    public ResponseEntity<Page<ProductResponseDTO>> list(
            Pageable pageable,
            @PathVariable Long restaurantId,
            @RequestParam(required = false) Boolean inactive
    ) {
        restaurantService.findById(restaurantId);
        Page<Product> products = productService.findAll(getPageableWithMappedSorts(pageable), restaurantId, inactive);
        return ResponseEntity.ok(products.map(productMapper::toResponseDTO));
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

    private Pageable getPageableWithMappedSorts(Pageable pageable) {
        HashMap<String, String> possibleSorts = new HashMap<>();
        possibleSorts.put("id", "id");
        possibleSorts.put("name", "name");
        possibleSorts.put("price", "price");
        possibleSorts.put("isActive", "isActive");

        return PageableUtils.mapSort(pageable, possibleSorts);
    }
}
