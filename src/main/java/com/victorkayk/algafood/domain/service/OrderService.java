package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.enums.StatusOrderEnum;
import com.victorkayk.algafood.domain.model.Order;

import java.util.List;

public interface OrderService {
    Order save(Order order);

    void delete(String uuid);

    List<Order> findAll();

    Order findByUuid(String uuid);

    Order update(String uuid, Order order);

    void updateStatus(String uuid, StatusOrderEnum status);
}
