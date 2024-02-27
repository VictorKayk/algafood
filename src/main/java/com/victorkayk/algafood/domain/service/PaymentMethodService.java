package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.PaymentMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentMethodService {
    PaymentMethod save(PaymentMethod paymentMethod);

    void delete(Long id);

    Page<PaymentMethod> findAll(Pageable pageable);

    PaymentMethod findById(Long id);

    PaymentMethod update(Long id, PaymentMethod paymentMethod);

    Page<PaymentMethod> findAllByRestaurantId(Pageable pageable, Long restaurantId);
}
