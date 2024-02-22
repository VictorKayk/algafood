package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.enums.ErrorEnum;
import com.victorkayk.algafood.domain.exception.ApiException;
import com.victorkayk.algafood.domain.model.Group;
import com.victorkayk.algafood.domain.repository.GroupRepository;
import com.victorkayk.algafood.domain.service.GroupService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Override
    @Transactional
    public Group save(Group city) {
        return groupRepository.save(city);
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
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group findById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorEnum.RESTAURANT_NOT_FOUND));
    }

    @Override
    @Transactional
    public Group update(Long id, Group city) {
        Group savedGroup = findById(id);
        BeanUtils.copyProperties(city, savedGroup, "id");
        return save(savedGroup);
    }
}
