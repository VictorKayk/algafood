package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant save(Restaurant restaurant);

    void delete(Long id);

    List<Restaurant> findAll();

    Restaurant findById(Long id);

    Restaurant update(Long id, Restaurant restaurant);

    void activate(Long id);

    void deactivate(Long id);

    void open(Long id);

    void close(Long id);

    void associatePaymentMethod(Long restaurantId, Long paymentMethodId);

    void disassociatePaymentMethod(Long restaurantId, Long paymentMethodId);
}
