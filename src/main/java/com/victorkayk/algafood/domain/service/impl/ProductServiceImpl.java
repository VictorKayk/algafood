package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.enums.ErrorEnum;
import com.victorkayk.algafood.domain.exception.ApiException;
import com.victorkayk.algafood.domain.model.Product;
import com.victorkayk.algafood.domain.model.Restaurant;
import com.victorkayk.algafood.domain.repository.ProductRepository;
import com.victorkayk.algafood.domain.service.ProductService;
import com.victorkayk.algafood.domain.service.RestaurantService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public Product save(Product Product) {
        Restaurant restaurant = restaurantService.findById(Product.getRestaurant().getId());
        Product.setRestaurant(restaurant);

        return productRepository.save(Product);
    }

    @Override
    @Transactional
    public void delete(Long restaurantId, Long id) {
        restaurantService.findById(restaurantId);
        Product Product = findById(restaurantId, id);

        try {
            productRepository.delete(Product);
            productRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ApiException(ErrorEnum.PRODUCT_IN_USE);
        }
    }

    @Override
    public Page<Product> findAll(Pageable pageable, Long restaurantId, Boolean inactive) {
        restaurantService.findById(restaurantId);

        if (inactive != null && inactive) return productRepository.findAll(pageable, restaurantId);
        return productRepository.findAllActive(pageable, restaurantId);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorEnum.PRODUCT_NOT_FOUND));
    }

    @Override
    public Product findById(Long restaurantId, Long id) {
        restaurantService.findById(restaurantId);

        return productRepository.findById(restaurantId, id)
                .orElseThrow(() -> new ApiException(ErrorEnum.PRODUCT_NOT_FOUND));
    }

    @Override
    @Transactional
    public Product update(Long restaurantId, Long id, Product Product) {
        Restaurant restaurant = restaurantService.findById(Product.getRestaurant().getId());
        Product.setRestaurant(restaurant);

        Product savedProduct = findById(restaurantId, id);

        BeanUtils.copyProperties(Product, savedProduct, "id");

        return productRepository.save(savedProduct);
    }
}
