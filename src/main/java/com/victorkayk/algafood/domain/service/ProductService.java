package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product save(Product Product);

    void delete(Long restaurantId, Long id);

    Page<Product> findAll(Pageable pageable, Long restaurantId, Boolean inactive);

    Product findById(Long id);

    Product findById(Long restaurantId, Long id);

    Product update(Long restaurantId, Long id, Product Product);
}
