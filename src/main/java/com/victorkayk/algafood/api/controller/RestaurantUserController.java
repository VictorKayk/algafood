package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.response.UserResponseDTO;
import com.victorkayk.algafood.api.mapper.UserMapper;
import com.victorkayk.algafood.api.util.PageableUtils;
import com.victorkayk.algafood.domain.model.User;
import com.victorkayk.algafood.domain.service.RestaurantService;
import com.victorkayk.algafood.domain.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Tag(name = "Restaurants", description = "Restaurant's responsible user endpoints")
@RestController
@RequestMapping("/restaurants/{restaurantId}/users")
public class RestaurantUserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> listResponsiblesFromRestaurant(
            Pageable pageable,
            @PathVariable Long restaurantId
    ) {
        restaurantService.findById(restaurantId);
        Page<User> users = userService.findAllByRestaurantId(pageable, restaurantId);
        return ResponseEntity.ok(users.map(userMapper::toResponseDTO));
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

    private Pageable getPageableWithMappedSorts(Pageable pageable) {
        HashMap<String, String> possibleSorts = new HashMap<>();
        possibleSorts.put("id", "id");
        possibleSorts.put("name", "name");

        return PageableUtils.mapSort(pageable, possibleSorts);
    }
}
