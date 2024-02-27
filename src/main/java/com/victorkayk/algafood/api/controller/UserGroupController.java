package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.response.GroupResponseDTO;
import com.victorkayk.algafood.api.mapper.GroupMapper;
import com.victorkayk.algafood.api.util.PageableUtils;
import com.victorkayk.algafood.domain.model.Group;
import com.victorkayk.algafood.domain.model.User;
import com.victorkayk.algafood.domain.service.GroupService;
import com.victorkayk.algafood.domain.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Tag(name = "Users", description = "User group endpoints")
@RestController
@RequestMapping("/users/{userId}/groups")
public class UserGroupController {
    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupMapper groupMapper;

    @GetMapping
    public ResponseEntity<Page<GroupResponseDTO>> listGroupsFromUser(
            Pageable pageable,
            @PathVariable Long userId
    ) {
        User user = userService.findById(userId);
        Page<Group> groups = groupService.findAllByUserId(pageable, userId);
        return ResponseEntity.ok(groups.map(groupMapper::toResponseDTO));
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

    private Pageable getPageableWithMappedSorts(Pageable pageable) {
        HashMap<String, String> possibleSorts = new HashMap<>();
        possibleSorts.put("id", "id");
        possibleSorts.put("name", "name");

        return PageableUtils.mapSort(pageable, possibleSorts);
    }
}
