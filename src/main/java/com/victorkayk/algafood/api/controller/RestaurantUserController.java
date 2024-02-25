package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.response.UserResponseDTO;
import com.victorkayk.algafood.api.mapper.UserMapper;
import com.victorkayk.algafood.domain.model.Restaurant;
import com.victorkayk.algafood.domain.service.RestaurantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Restaurants", description = "Restaurant's responsible user endpoints")
@RestController
@RequestMapping("/restaurants/{restaurantId}/users")
public class RestaurantUserController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listResponsiblesFromRestaurant(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        return ResponseEntity.ok(userMapper.toResponseDTO(restaurant.getUsers()));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> associateResponsible(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.associateUser(restaurantId, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> disassociateResponsible(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.disassociateUser(restaurantId, userId);
        return ResponseEntity.noContent().build();
    }
}
