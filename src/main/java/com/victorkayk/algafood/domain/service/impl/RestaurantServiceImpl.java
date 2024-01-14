package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.model.Restaurant;
import com.victorkayk.algafood.domain.repository.RestaurantRepository;
import com.victorkayk.algafood.domain.service.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(Long id) {
        Restaurant restaurant = findById(id);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
    }

    @Override
    public Restaurant update(Long id, Restaurant restaurant) {
        Restaurant savedRestaurant = findById(id);
        BeanUtils.copyProperties(restaurant, savedRestaurant, "id");
        return save(savedRestaurant);
    }
}
