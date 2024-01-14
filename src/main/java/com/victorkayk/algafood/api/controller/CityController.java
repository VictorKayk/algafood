package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.domain.model.City;
import com.victorkayk.algafood.domain.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> list() {
        return ResponseEntity.ok(cityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(cityService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<City> save(@RequestBody City city) {
        return ResponseEntity.ok(cityService.save(city));
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> update(@PathVariable Long id, @RequestBody City city) {
        try {
            return ResponseEntity.ok(cityService.update(id, city));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
