package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.Order;

import java.util.List;

public interface OrderService {
    Order save(Order order);

    void delete(Long id);

    List<Order> findAll();

    Order findById(Long id);

    Order update(Long id, Order order);
}
