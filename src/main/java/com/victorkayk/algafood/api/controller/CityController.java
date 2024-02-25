package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.request.CityCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.CityUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.CityResponseDTO;
import com.victorkayk.algafood.api.mapper.CityMapper;
import com.victorkayk.algafood.domain.model.City;
import com.victorkayk.algafood.domain.service.CityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cities", description = "City endpoints")
@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @Autowired
    private CityMapper cityMapper;

    @GetMapping
    public ResponseEntity<List<CityResponseDTO>> list() {
        List<City> cities = cityService.findAll();
        return ResponseEntity.ok(cityMapper.toResponseDTO(cities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cityMapper.toResponseDTO(cityService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CityResponseDTO> save(@RequestBody CityCreateRequestDTO dto) {
        City city = cityMapper.createRequestDTOToEntity(dto);
        return new ResponseEntity<>(cityMapper.toResponseDTO(cityService.save(city)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityResponseDTO> update(@PathVariable Long id, @RequestBody CityUpdateRequestDTO dto) {
        City city = cityMapper.updateRequestDTOToEntity(dto);
        return ResponseEntity.ok(cityMapper.toResponseDTO(cityService.update(id, city)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
