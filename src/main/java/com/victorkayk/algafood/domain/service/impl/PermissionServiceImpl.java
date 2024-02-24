package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.enums.ErrorEnum;
import com.victorkayk.algafood.domain.exception.ApiException;
import com.victorkayk.algafood.domain.model.Permission;
import com.victorkayk.algafood.domain.repository.PermissionRepository;
import com.victorkayk.algafood.domain.service.PermissionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    @Transactional
    public Permission save(Permission Permission) {
        return permissionRepository.save(Permission);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Permission Permission = findById(id);

        try {
            permissionRepository.delete(Permission);
            permissionRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ApiException(ErrorEnum.PERMISSION_IN_USE);
        }
    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission findById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorEnum.PERMISSION_NOT_FOUND));
    }

    @Override
    @Transactional
    public Permission update(Long id, Permission Permission) {
        Permission savedPermission = findById(id);
        BeanUtils.copyProperties(Permission, savedPermission, "id");
        return permissionRepository.save(savedPermission);
    }
}
