package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.enums.ErrorEnum;
import com.victorkayk.algafood.domain.exception.ApiException;
import com.victorkayk.algafood.domain.model.Group;
import com.victorkayk.algafood.domain.model.User;
import com.victorkayk.algafood.domain.repository.UserRepository;
import com.victorkayk.algafood.domain.service.GroupService;
import com.victorkayk.algafood.domain.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository stateRepository;

    @Autowired
    private GroupService groupService;

    @Override
    @Transactional
    public User save(User user) {
        Optional<User> existingUser = stateRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent() && !existingUser.get().equals(user)) {
            throw new ApiException(ErrorEnum.USER_ALREADY_EXISTS);
        }

        return stateRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User state = findById(id);

        try {
            stateRepository.delete(state);
            stateRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ApiException(ErrorEnum.STATE_IN_USE);
        }
    }

    @Override
    public List<User> findAll() {
        return stateRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return stateRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorEnum.RESTAURANT_NOT_FOUND));
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        User savedUser = findById(id);
        BeanUtils.copyProperties(user, savedUser, "id");
        return stateRepository.save(savedUser);
    }

    @Override
    @Transactional
    public void updatePassword(Long id, String password, String newPassword) {
        User user = findById(id);

        if (user.passwordNotMatch(password)) {
            throw new ApiException(ErrorEnum.INVALID_PASSWORD);
        }

        user.setPassword(newPassword);
    }

    @Override
    @Transactional
    public void associateGroup(Long userId, Long groupId) {
        User user = findById(userId);
        Group group = groupService.findById(groupId);
        user.associateGroup(group);
    }

    @Override
    @Transactional
    public void disassociateGroup(Long userId, Long groupId) {
        User user = findById(userId);
        Group group = groupService.findById(groupId);
        user.disassociateGroup(group);
    }
}
