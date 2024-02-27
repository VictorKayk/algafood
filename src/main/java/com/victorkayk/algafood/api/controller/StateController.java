package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.request.StateCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.StateUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.StateResponseDTO;
import com.victorkayk.algafood.api.mapper.StateMapper;
import com.victorkayk.algafood.api.util.PageableUtils;
import com.victorkayk.algafood.domain.model.State;
import com.victorkayk.algafood.domain.service.StateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Tag(name = "States", description = "State endpoints")
@RestController
@RequestMapping("/states")
public class StateController {
    @Autowired
    private StateService stateService;

    @Autowired
    private StateMapper stateMapper;

    @GetMapping
    public ResponseEntity<Page<StateResponseDTO>> list(Pageable pageable) {
        Page<State> states = stateService.findAll(pageable);
        return ResponseEntity.ok(states.map(stateMapper::toResponseDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(stateMapper.toResponseDTO(stateService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<StateResponseDTO> save(@RequestBody StateCreateRequestDTO dto) {
        State state = stateMapper.createRequestDTOToEntity(dto);
        return new ResponseEntity<>(stateMapper.toResponseDTO(stateService.save(state)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateResponseDTO> update(@PathVariable Long id, @RequestBody StateUpdateRequestDTO dto) {
        State state = stateMapper.updateRequestDTOToEntity(dto);
        return ResponseEntity.ok(stateMapper.toResponseDTO(stateService.update(id, state)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stateService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private Pageable getPageableWithMappedSorts(Pageable pageable) {
        HashMap<String, String> possibleSorts = new HashMap<>();
        possibleSorts.put("id", "id");
        possibleSorts.put("name", "name");

        return PageableUtils.mapSort(pageable, possibleSorts);
    }
}
