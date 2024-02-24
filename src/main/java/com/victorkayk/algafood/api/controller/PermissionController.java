package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.request.PermissionCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.PermissionUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.PermissionResponseDTO;
import com.victorkayk.algafood.api.mapper.PermissionMapper;
import com.victorkayk.algafood.domain.model.Permission;
import com.victorkayk.algafood.domain.service.PermissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Permissions", description = "Permission endpoints")
@RestController
@RequestMapping("/permissions")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionMapper permissionMapper;

    @GetMapping
    public ResponseEntity<List<PermissionResponseDTO>> list() {
        List<Permission> permissions = permissionService.findAll();
        return ResponseEntity.ok(
                permissions.stream().map(permissionMapper::toResponseDTO).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(permissionMapper.toResponseDTO(permissionService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PermissionResponseDTO> save(@RequestBody PermissionCreateRequestDTO dto) {
        Permission permission = permissionMapper.createRequestDTOToEntity(dto);
        return new ResponseEntity<>(permissionMapper.toResponseDTO(permissionService.save(permission)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionResponseDTO> update(@PathVariable Long id, @RequestBody PermissionUpdateRequestDTO dto) {
        Permission permission = permissionMapper.updateRequestDTOToEntity(dto);
        return ResponseEntity.ok(permissionMapper.toResponseDTO(permissionService.update(id, permission)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        permissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
