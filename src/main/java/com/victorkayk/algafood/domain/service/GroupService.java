package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.Group;
import com.victorkayk.algafood.domain.model.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {
    Group save(Group state);

    void delete(Long id);

    Page<Group> findAll(Pageable pageable);

    Group findById(Long id);

    Group update(Long id, Group group);

    void associatePermission(Long groupId, Long permissionId);

    void disassociatePermission(Long groupId, Long permissionId);

    Page<Permission> findPermissionsByGroupId(Pageable pageable, Long groupId);

    Page<Group> findAllByUserId(Pageable pageable, Long userId);
}
