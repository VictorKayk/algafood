package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.request.UserCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.UserPasswordUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.request.UserUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.UserResponseDTO;
import com.victorkayk.algafood.api.mapper.UserMapper;
import com.victorkayk.algafood.domain.model.User;
import com.victorkayk.algafood.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> list() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(
                users.stream().map(userMapper::toResponseDTO).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userMapper.toResponseDTO(userService.findById(id)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@RequestBody UserCreateRequestDTO dto) {
        User user = userMapper.createRequestDTOToEntity(dto);
        return new ResponseEntity<>(userMapper.toResponseDTO(userService.save(user)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody UserUpdateRequestDTO dto) {
        try {
            User user = userMapper.updateRequestDTOToEntity(dto);
            return ResponseEntity.ok(userMapper.toResponseDTO(userService.update(id, user)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UserPasswordUpdateRequestDTO dto) {
        userService.updatePassword(id, dto.password(), dto.newPassword());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}