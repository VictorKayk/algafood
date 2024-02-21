package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.request.StatePostRequestDTO;
import com.victorkayk.algafood.api.dto.request.StatePutRequestDTO;
import com.victorkayk.algafood.api.dto.response.StateResponseDTO;
import com.victorkayk.algafood.api.mapper.StateMapper;
import com.victorkayk.algafood.domain.model.State;
import com.victorkayk.algafood.domain.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {
    @Autowired
    private StateService stateService;

    @Autowired
    private StateMapper stateMapper;

    @GetMapping
    public ResponseEntity<List<StateResponseDTO>> list() {
        List<State> states = stateService.findAll();
        return ResponseEntity.ok(
                states.stream().map(stateMapper::toResponseDTO).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateResponseDTO> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(stateMapper.toResponseDTO(stateService.findById(id)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<StateResponseDTO> save(@RequestBody StatePostRequestDTO dto) {
        State state = stateMapper.postRequestDTOToEntity(dto);
        return new ResponseEntity<>(stateMapper.toResponseDTO(stateService.save(state)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateResponseDTO> update(@PathVariable Long id, @RequestBody StatePutRequestDTO dto) {
        try {
            State state = stateMapper.putRequestDTOToEntity(dto);
            return ResponseEntity.ok(stateMapper.toResponseDTO(stateService.update(id, state)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
