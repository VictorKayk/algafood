package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.request.KitchenCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.KitchenUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.KitchenResponseDTO;
import com.victorkayk.algafood.api.mapper.KitchenMapper;
import com.victorkayk.algafood.api.util.PageableUtils;
import com.victorkayk.algafood.domain.model.Kitchen;
import com.victorkayk.algafood.domain.service.KitchenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Tag(name = "Kitchens", description = "Kitchen endpoints")
@RestController
@RequestMapping("/kitchens")
public class KitchenController {
    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private KitchenMapper kitchenMapper;

    @GetMapping
    public ResponseEntity<Page<KitchenResponseDTO>> list(Pageable pageable) {
        Page<Kitchen> kitchens = kitchenService.findAll(getPageableWithMappedSorts(pageable));
        return ResponseEntity.ok(kitchens.map(kitchenMapper::toResponseDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KitchenResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(kitchenMapper.toResponseDTO(kitchenService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<KitchenResponseDTO> save(@RequestBody KitchenCreateRequestDTO dto) {
        Kitchen kitchen = kitchenMapper.createRequestDTOToEntity(dto);
        return new ResponseEntity<>(kitchenMapper.toResponseDTO(kitchenService.save(kitchen)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KitchenResponseDTO> update(@PathVariable Long id, @RequestBody KitchenUpdateRequestDTO dto) {
        Kitchen kitchen = kitchenMapper.updateRequestDTOToEntity(dto);
        return ResponseEntity.ok(kitchenMapper.toResponseDTO(kitchenService.update(id, kitchen)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        kitchenService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private Pageable getPageableWithMappedSorts(Pageable pageable) {
        HashMap<String, String> possibleSorts = new HashMap<>();
        possibleSorts.put("id", "id");
        possibleSorts.put("name", "name");

        return PageableUtils.mapSort(pageable, possibleSorts);
    }
}
