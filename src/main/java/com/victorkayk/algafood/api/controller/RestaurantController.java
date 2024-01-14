package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.domain.model.Restaurant;
import com.victorkayk.algafood.domain.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> list() {
        return ResponseEntity.ok(restaurantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(restaurantService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Restaurant> save(@RequestBody Restaurant restaurant) {
        return ResponseEntity.ok(restaurantService.save(restaurant));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        try {
            return ResponseEntity.ok(restaurantService.update(id, restaurant));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
