package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.dto.OrderFilterDTO;
import com.victorkayk.algafood.domain.enums.StatusOrderEnum;
import com.victorkayk.algafood.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Order save(Order order);

    void delete(String uuid);

    Page<Order> findAll(Pageable pageable, OrderFilterDTO dto);

    Order findByUuid(String uuid);

    Order update(String uuid, Order order);

    void updateStatus(String uuid, StatusOrderEnum status);
}
