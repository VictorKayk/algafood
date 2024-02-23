package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {
    PaymentMethod save(PaymentMethod paymentMethod);

    void delete(Long id);

    List<PaymentMethod> findAll();

    PaymentMethod findById(Long id);

    PaymentMethod update(Long id, PaymentMethod paymentMethod);
}
