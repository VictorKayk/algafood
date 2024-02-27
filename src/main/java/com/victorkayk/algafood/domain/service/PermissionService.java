package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermissionService {
    Permission save(Permission permission);

    void delete(Long id);

    Page<Permission> findAll(Pageable pageable);

    Permission findById(Long id);

    Permission update(Long id, Permission permission);

    Page<Permission> findPermissionsByGroupId(Pageable pageable, Long groupId);
}
