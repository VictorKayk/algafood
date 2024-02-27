package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.Kitchen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface KitchenService {
    Kitchen save(Kitchen kitchen);

    void delete(Long id);

    Page<Kitchen> findAll(Pageable pageable);

    Kitchen findById(Long id);

    Kitchen update(Long id, Kitchen kitchen);
}
