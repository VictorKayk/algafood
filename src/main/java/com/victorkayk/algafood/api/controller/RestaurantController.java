package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.request.RestaurantPostRequestDTO;
import com.victorkayk.algafood.api.dto.request.RestaurantPutRequestDTO;
import com.victorkayk.algafood.api.dto.response.RestaurantResponseDTO;
import com.victorkayk.algafood.api.mapper.RestaurantMapper;
import com.victorkayk.algafood.domain.model.Restaurant;
import com.victorkayk.algafood.domain.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDTO>> list() {
        List<Restaurant> restaurants = restaurantService.findAll();
        return ResponseEntity.ok(
                restaurants.stream().map(restaurantMapper::toResponseDTO).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(restaurantMapper.toResponseDTO(restaurantService.findById(id)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<RestaurantResponseDTO> save(@RequestBody RestaurantPostRequestDTO dto) {
        Restaurant restaurant = restaurantMapper.postRequestDTOToEntity(dto);
        return new ResponseEntity<>(restaurantMapper.toResponseDTO(restaurantService.save(restaurant)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> update(@PathVariable Long id, @RequestBody RestaurantPutRequestDTO dto) {
        try {
            Restaurant restaurant = restaurantMapper.putRequestDTOToEntity(dto);
            return ResponseEntity.ok(restaurantMapper.toResponseDTO(restaurantService.update(id, restaurant)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
