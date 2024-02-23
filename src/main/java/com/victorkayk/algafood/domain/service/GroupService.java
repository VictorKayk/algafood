package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.Group;

import java.util.List;

public interface GroupService {
    Group save(Group state);

    void delete(Long id);

    List<Group> findAll();

    Group findById(Long id);

    Group update(Long id, Group group);

    void associatePermission(Long groupId, Long permissionId);

    void disassociatePermission(Long groupId, Long permissionId);
}
