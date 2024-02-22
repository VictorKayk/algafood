package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    void delete(Long id);

    List<User> findAll();

    User findById(Long id);

    User update(Long id, User user);

    void updatePassword(Long id, String password, String newPassword);
}
