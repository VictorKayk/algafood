package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.enums.ErrorEnum;
import com.victorkayk.algafood.domain.exception.ApiException;
import com.victorkayk.algafood.domain.model.Group;
import com.victorkayk.algafood.domain.model.Permission;
import com.victorkayk.algafood.domain.repository.GroupRepository;
import com.victorkayk.algafood.domain.service.GroupService;
import com.victorkayk.algafood.domain.service.PermissionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PermissionService permissionService;

    @Override
    @Transactional
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Group group = findById(id);

        try {
            groupRepository.delete(group);
            groupRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ApiException(ErrorEnum.STATE_IN_USE);
        }
    }

    @Override
    public Page<Group> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    @Override
    public Group findById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorEnum.RESTAURANT_NOT_FOUND));
    }

    @Override
    @Transactional
    public Group update(Long id, Group group) {
        Group savedGroup = findById(id);
        BeanUtils.copyProperties(group, savedGroup, "id");
        return groupRepository.save(savedGroup);
    }

    @Override
    @Transactional
    public void associatePermission(Long groupId, Long permissionId) {
        Group group = findById(groupId);
        Permission permission = permissionService.findById(permissionId);
        group.associatePermission(permission);
    }

    @Override
    @Transactional
    public void disassociatePermission(Long groupId, Long permissionId) {
        Group group = findById(groupId);
        Permission permission = permissionService.findById(permissionId);
        group.disassociatePermission(permission);
    }

    @Override
    public Page<Permission> findPermissionsByGroupId(Pageable pageable, Long groupId) {
        return permissionService.findPermissionsByGroupId(pageable, groupId);
    }

    @Override
    public Page<Group> findAllByUserId(Pageable pageable, Long userId) {
        return groupRepository.findAllByUserId(pageable, userId);
    }
}
