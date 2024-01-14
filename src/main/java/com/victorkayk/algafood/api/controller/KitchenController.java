package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.domain.model.Kitchen;
import com.victorkayk.algafood.domain.service.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {
    @Autowired
    private KitchenService kitchenService;

    @GetMapping
    public ResponseEntity<List<Kitchen>> list() {
        return ResponseEntity.ok(kitchenService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kitchen> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(kitchenService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Kitchen> save(@RequestBody Kitchen kitchen) {
        return ResponseEntity.ok(kitchenService.save(kitchen));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kitchen> update(@PathVariable Long id, @RequestBody Kitchen kitchen) {
        try {
            return ResponseEntity.ok(kitchenService.update(id, kitchen));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
