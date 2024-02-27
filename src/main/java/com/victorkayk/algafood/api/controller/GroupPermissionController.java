package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.response.PermissionResponseDTO;
import com.victorkayk.algafood.api.mapper.PermissionMapper;
import com.victorkayk.algafood.api.util.PageableUtils;
import com.victorkayk.algafood.domain.model.Permission;
import com.victorkayk.algafood.domain.service.GroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Groups", description = "Group permission endpoints")
@RestController
@RequestMapping("/groups/{groupId}/permissions")
public class GroupPermissionController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionMapper permissionMapper;

    @GetMapping
    public ResponseEntity<Page<PermissionResponseDTO>> listPermissionsFromGroup(Pageable pageable, @PathVariable Long groupId) {
        groupService.findById(groupId);
        Page<Permission> permissions = groupService.findPermissionsByGroupId(getPageableWithMappedSorts(pageable), groupId);
        return ResponseEntity.ok(permissions.map(permissionMapper::toResponseDTO));
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

    private Pageable getPageableWithMappedSorts(Pageable pageable) {
        Map<String, String> possibleSorts = new HashMap<>();
        possibleSorts.put("id", "id");
        possibleSorts.put("name", "name");

        return PageableUtils.mapSort(pageable, possibleSorts);
    }
}
