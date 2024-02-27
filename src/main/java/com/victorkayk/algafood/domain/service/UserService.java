package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User save(User user);

    void delete(Long id);

    Page<User> findAll(Pageable pageable);

    User findById(Long id);

    User update(Long id, User user);

    void updatePassword(Long id, String password, String newPassword);

    void associateGroup(Long userId, Long groupId);

    void disassociateGroup(Long userId, Long groupId);

    Page<User> findAllByRestaurantId(Pageable pageable, Long restaurantId);
}
