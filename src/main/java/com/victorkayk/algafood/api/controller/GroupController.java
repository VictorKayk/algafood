package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.request.GroupCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.GroupUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.GroupResponseDTO;
import com.victorkayk.algafood.api.mapper.GroupMapper;
import com.victorkayk.algafood.domain.model.Group;
import com.victorkayk.algafood.domain.service.GroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Groups", description = "Group endpoints")
@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupMapper groupMapper;

    @GetMapping
    public ResponseEntity<List<GroupResponseDTO>> list() {
        List<Group> groups = groupService.findAll();
        return ResponseEntity.ok(
                groups.stream().map(groupMapper::toResponseDTO).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(groupMapper.toResponseDTO(groupService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<GroupResponseDTO> save(@RequestBody GroupCreateRequestDTO dto) {
        Group group = groupMapper.createRequestDTOToEntity(dto);
        return new ResponseEntity<>(groupMapper.toResponseDTO(groupService.save(group)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupResponseDTO> update(@PathVariable Long id, @RequestBody GroupUpdateRequestDTO dto) {
        Group group = groupMapper.updateRequestDTOToEntity(dto);
        return ResponseEntity.ok(groupMapper.toResponseDTO(groupService.update(id, group)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        groupService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
