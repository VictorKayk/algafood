package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.Product;

import java.util.List;

public interface ProductService {
    Product save(Product Product);

    void delete(Long restaurantId, Long id);

    List<Product> findAll(Long restaurantId, Boolean inactive);

    Product findById(Long id);

    Product findById(Long restaurantId, Long id);

    Product update(Long restaurantId, Long id, Product Product);
}
