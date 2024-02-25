package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.response.GroupResponseDTO;
import com.victorkayk.algafood.api.mapper.GroupMapper;
import com.victorkayk.algafood.domain.model.User;
import com.victorkayk.algafood.domain.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "User group endpoints")
@RestController
@RequestMapping("/users/{userId}/groups")
public class UserGroupController {
    @Autowired
    private UserService userService;

    @Autowired
    private GroupMapper groupMapper;

    @GetMapping
    public ResponseEntity<List<GroupResponseDTO>> listGroupsFromUser(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok(groupMapper.toResponseDTO(user.getGroups()));
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<Void> associateGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.associateGroup(userId, groupId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> disassociateGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.disassociateGroup(userId, groupId);
        return ResponseEntity.noContent().build();
    }
}
