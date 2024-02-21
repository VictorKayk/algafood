package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.request.KitchenPostRequestDTO;
import com.victorkayk.algafood.api.dto.request.KitchenPutRequestDTO;
import com.victorkayk.algafood.api.dto.response.KitchenResponseDTO;
import com.victorkayk.algafood.api.mapper.KitchenMapper;
import com.victorkayk.algafood.domain.model.Kitchen;
import com.victorkayk.algafood.domain.service.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {
    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private KitchenMapper kitchenMapper;

    @GetMapping
    public ResponseEntity<List<KitchenResponseDTO>> list() {
        List<Kitchen> kitchens = kitchenService.findAll();
        return ResponseEntity.ok(
                kitchens.stream().map(kitchenMapper::toResponseDTO).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<KitchenResponseDTO> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(kitchenMapper.toResponseDTO(kitchenService.findById(id)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<KitchenResponseDTO> save(@RequestBody KitchenPostRequestDTO dto) {
        Kitchen kitchen = kitchenMapper.postRequestDTOToEntity(dto);
        return new ResponseEntity<>(kitchenMapper.toResponseDTO(kitchenService.save(kitchen)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KitchenResponseDTO> update(@PathVariable Long id, @RequestBody KitchenPutRequestDTO dto) {
        try {
            Kitchen kitchen = kitchenMapper.putRequestDTOToEntity(dto);
            return ResponseEntity.ok(kitchenMapper.toResponseDTO(kitchenService.update(id, kitchen)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        kitchenService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
