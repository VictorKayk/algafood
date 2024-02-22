package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.enums.ErrorEnum;
import com.victorkayk.algafood.domain.exception.ApiException;
import com.victorkayk.algafood.domain.model.PaymentMethod;
import com.victorkayk.algafood.domain.repository.PaymentMethodRepository;
import com.victorkayk.algafood.domain.service.PaymentMethodService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
    @Autowired
    private PaymentMethodRepository PaymentMethodRepository;

    @Override
    @Transactional
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return PaymentMethodRepository.save(paymentMethod);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        PaymentMethod PaymentMethod = findById(id);

        try {
            PaymentMethodRepository.delete(PaymentMethod);
            PaymentMethodRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ApiException(ErrorEnum.PAYMENT_METHOD_IN_USE);
        }
    }

    @Override
    public List<PaymentMethod> findAll() {
        return PaymentMethodRepository.findAll();
    }

    @Override
    public PaymentMethod findById(Long id) {
        return PaymentMethodRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorEnum.PAYMENT_METHOD_NOT_FOUND));
    }

    @Override
    @Transactional
    public PaymentMethod update(Long id, PaymentMethod paymentMethod) {
        PaymentMethod savedPaymentMethod = findById(id);
        BeanUtils.copyProperties(paymentMethod, savedPaymentMethod, "id");
        return save(savedPaymentMethod);
    }
}
