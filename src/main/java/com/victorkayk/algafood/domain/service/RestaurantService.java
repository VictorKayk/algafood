package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantService {
    Restaurant save(Restaurant restaurant);

    void delete(Long id);

    Page<Restaurant> findAll(Pageable pageable);

    Restaurant findById(Long id);

    Restaurant update(Long id, Restaurant restaurant);

    void activate(Long id);

    void activate(List<Long> ids);

    void deactivate(Long id);

    void deactivate(List<Long> ids);

    void open(Long id);

    void close(Long id);

    void associatePaymentMethod(Long restaurantId, Long paymentMethodId);

    void disassociatePaymentMethod(Long restaurantId, Long paymentMethodId);

    void associateUser(Long restaurantId, Long userId);

    void disassociateUser(Long restaurantId, Long userId);
}
