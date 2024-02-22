package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.enums.ErrorEnum;
import com.victorkayk.algafood.domain.exception.ApiException;
import com.victorkayk.algafood.domain.model.Kitchen;
import com.victorkayk.algafood.domain.repository.KitchenRepository;
import com.victorkayk.algafood.domain.service.KitchenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenServiceImpl implements KitchenService {
    @Autowired
    private KitchenRepository kitchenRepository;

    @Override
    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Kitchen kitchen = findById(id);

        try {
            kitchenRepository.delete(kitchen);
            kitchenRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ApiException(ErrorEnum.KITCHEN_IN_USE);
        }
    }

    @Override
    public List<Kitchen> findAll() {
        return kitchenRepository.findAll();
    }

    @Override
    public Kitchen findById(Long id) {
        return kitchenRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorEnum.KITCHEN_NOT_FOUND));
    }

    @Override
    @Transactional
    public Kitchen update(Long id, Kitchen kitchen) {
        Kitchen savedKitchen = findById(id);
        BeanUtils.copyProperties(kitchen, savedKitchen, "id");
        return save(savedKitchen);
    }
}
