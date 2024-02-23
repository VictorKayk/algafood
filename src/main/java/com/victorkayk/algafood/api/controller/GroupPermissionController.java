package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.response.PermissionResponseDTO;
import com.victorkayk.algafood.api.mapper.PermissionMapper;
import com.victorkayk.algafood.domain.model.Group;
import com.victorkayk.algafood.domain.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups/{groupId}/permissions")
public class GroupPermissionController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionMapper permissionMapper;

    @GetMapping
    public ResponseEntity<List<PermissionResponseDTO>> listPermissionsFromGroup(@PathVariable Long groupId) {
        Group group = groupService.findById(groupId);
        return ResponseEntity.ok(
                group.getPermissions().stream().map(permissionMapper::toResponseDTO).toList()
        );
    }

    @PutMapping("/{permissionId}")
    public ResponseEntity<Void> associatePermission(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.associatePermission(groupId, permissionId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<Void> disassociatePermission(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.disassociatePermission(groupId, permissionId);
        return ResponseEntity.noContent().build();
    }
}
