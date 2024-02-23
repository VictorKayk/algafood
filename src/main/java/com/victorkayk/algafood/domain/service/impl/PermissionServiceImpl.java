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
    private PermissionRepository PermissionRepository;

    @Override
    @Transactional
    public Permission save(Permission Permission) {
        return PermissionRepository.save(Permission);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Permission Permission = findById(id);

        try {
            PermissionRepository.delete(Permission);
            PermissionRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ApiException(ErrorEnum.PERMISSION_IN_USE);
        }
    }

    @Override
    public List<Permission> findAll() {
        return PermissionRepository.findAll();
    }

    @Override
    public Permission findById(Long id) {
        return PermissionRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorEnum.PERMISSION_NOT_FOUND));
    }

    @Override
    @Transactional
    public Permission update(Long id, Permission Permission) {
        Permission savedPermission = findById(id);
        BeanUtils.copyProperties(Permission, savedPermission, "id");
        return save(savedPermission);
    }
}
