package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.enums.ErrorEnum;
import com.victorkayk.algafood.domain.exception.ApiException;
import com.victorkayk.algafood.domain.model.Kitchen;
import com.victorkayk.algafood.domain.model.Restaurant;
import com.victorkayk.algafood.domain.repository.RestaurantRepository;
import com.victorkayk.algafood.domain.service.KitchenService;
import com.victorkayk.algafood.domain.service.RestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenService kitchenService;

    @Override
    public Restaurant save(Restaurant restaurant) {
        Kitchen kitchen = kitchenService.findById(restaurant.getKitchen().getId());
        restaurant.setKitchen(kitchen);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(Long id) {
        Restaurant restaurant = findById(id);

        try {
            restaurantRepository.delete(restaurant);
            restaurantRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ApiException(ErrorEnum.RESTAURANT_IN_USE);
        }
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorEnum.RESTAURANT_NOT_FOUND));
    }

    @Override
    public Restaurant update(Long id, Restaurant restaurant) {
        Restaurant savedRestaurant = findById(id);
        BeanUtils.copyProperties(restaurant, savedRestaurant, "id");
        return save(savedRestaurant);
    }

    @Override
    public void activate(Long id) {
        Restaurant restaurant = findById(id);
        restaurant.activate();
    }

    @Override
    public void deactivate(Long id) {
        Restaurant restaurant = findById(id);
        restaurant.deactivate();
    }
}
