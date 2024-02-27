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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupService groupService;

    @Override
    @Transactional
    public User save(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent() && !existingUser.get().equals(user)) {
            throw new ApiException(ErrorEnum.USER_ALREADY_EXISTS);
        }

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User state = findById(id);

        try {
            userRepository.delete(state);
            userRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ApiException(ErrorEnum.STATE_IN_USE);
        }
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorEnum.RESTAURANT_NOT_FOUND));
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        User savedUser = findById(id);
        BeanUtils.copyProperties(user, savedUser, "id");
        return userRepository.save(savedUser);
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

    @Override
    public Page<User> findAllByRestaurantId(Pageable pageable, Long restaurantId) {
        return userRepository.findAllByRestaurantId(pageable, restaurantId);
    }
}
