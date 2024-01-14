package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.domain.model.State;
import com.victorkayk.algafood.domain.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {
    @Autowired
    private StateService stateService;

    @GetMapping
    public ResponseEntity<List<State>> list() {
        return ResponseEntity.ok(stateService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(stateService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<State> save(@RequestBody State state) {
        return ResponseEntity.ok(stateService.save(state));
    }

    @PutMapping("/{id}")
    public ResponseEntity<State> update(@PathVariable Long id, @RequestBody State state) {
        try {
            return ResponseEntity.ok(stateService.update(id, state));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
