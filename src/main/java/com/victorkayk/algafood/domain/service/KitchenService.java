package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.Kitchen;

import java.util.List;

public interface KitchenService {
    Kitchen save(Kitchen kitchen);

    void delete(Long id);

    List<Kitchen> findAll();

    Kitchen findById(Long id);

    Kitchen update(Long id, Kitchen kitchen);
}
