package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.model.Kitchen;
import com.victorkayk.algafood.domain.repository.KitchenRepository;
import com.victorkayk.algafood.domain.service.KitchenService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenServiceImpl implements KitchenService {
    @Autowired
    private KitchenRepository kitchenRepository;

    @Override
    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    @Override
    public void delete(Long id) {
        Kitchen kitchen = findById(id);
        kitchenRepository.delete(kitchen);
    }

    @Override
    public List<Kitchen> findAll() {
        return kitchenRepository.findAll();
    }

    @Override
    public Kitchen findById(Long id) {
        return kitchenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Kitchen not found"));
    }

    @Override
    public Kitchen update(Long id, Kitchen kitchen) {
        Kitchen savedKitchen = findById(id);
        BeanUtils.copyProperties(kitchen, savedKitchen, "id");
        return save(savedKitchen);
    }
}
