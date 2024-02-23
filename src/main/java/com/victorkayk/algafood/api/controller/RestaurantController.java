package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.request.IdsRequestDTO;
import com.victorkayk.algafood.api.dto.request.RestaurantCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.RestaurantUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.RestaurantResponseDTO;
import com.victorkayk.algafood.api.mapper.RestaurantMapper;
import com.victorkayk.algafood.domain.model.Restaurant;
import com.victorkayk.algafood.domain.service.RestaurantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Restaurants", description = "Restaurant endpoints")
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
    public ResponseEntity<RestaurantResponseDTO> save(@RequestBody RestaurantCreateRequestDTO dto) {
        Restaurant restaurant = restaurantMapper.createRequestDTOToEntity(dto);
        return new ResponseEntity<>(restaurantMapper.toResponseDTO(restaurantService.save(restaurant)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> update(@PathVariable Long id, @RequestBody RestaurantUpdateRequestDTO dto) {
        try {
            Restaurant restaurant = restaurantMapper.updateRequestDTOToEntity(dto);
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

    @PutMapping("/activate")
    public ResponseEntity<Void> activateRestaurants(@RequestBody IdsRequestDTO dto) {
        restaurantService.activate(dto.ids());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        restaurantService.activate(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/deactivate")
    public ResponseEntity<Void> deactivateRestaurants(@RequestBody IdsRequestDTO dto) {
        restaurantService.deactivate(dto.ids());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        restaurantService.deactivate(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/open")
    public ResponseEntity<Void> open(@PathVariable Long id) {
        restaurantService.open(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/close")
    public ResponseEntity<Void> close(@PathVariable Long id) {
        restaurantService.close(id);
        return ResponseEntity.noContent().build();
    }
}
