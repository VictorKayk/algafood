package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.Permission;

import java.util.List;

public interface PermissionService {
    Permission save(Permission permission);

    void delete(Long id);

    List<Permission> findAll();

    Permission findById(Long id);

    Permission update(Long id, Permission permission);
}
