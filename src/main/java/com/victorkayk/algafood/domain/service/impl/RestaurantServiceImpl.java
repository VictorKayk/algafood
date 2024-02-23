package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.enums.ErrorEnum;
import com.victorkayk.algafood.domain.exception.ApiException;
import com.victorkayk.algafood.domain.model.City;
import com.victorkayk.algafood.domain.model.Kitchen;
import com.victorkayk.algafood.domain.model.Restaurant;
import com.victorkayk.algafood.domain.repository.RestaurantRepository;
import com.victorkayk.algafood.domain.service.CityService;
import com.victorkayk.algafood.domain.service.KitchenService;
import com.victorkayk.algafood.domain.service.PaymentMethodService;
import com.victorkayk.algafood.domain.service.RestaurantService;
import jakarta.transaction.Transactional;
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

    @Autowired
    private CityService cityService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Kitchen kitchen = kitchenService.findById(restaurant.getKitchen().getId());
        City city = cityService.findById(restaurant.getAddress().getCity().getId());

        restaurant.setKitchen(kitchen);
        restaurant.getAddress().setCity(city);

        return restaurantRepository.save(restaurant);
    }

    @Override
    @Transactional
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
    @Transactional
    public Restaurant update(Long id, Restaurant restaurant) {
        Restaurant savedRestaurant = findById(id);
        Kitchen kitchen = kitchenService.findById(restaurant.getKitchen().getId());
        City city = cityService.findById(restaurant.getAddress().getCity().getId());

        restaurant.setKitchen(kitchen);
        restaurant.getAddress().setCity(city);

        BeanUtils.copyProperties(restaurant, savedRestaurant, "id");

        return save(savedRestaurant);
    }

    @Override
    @Transactional
    public void activate(Long id) {
        Restaurant restaurant = findById(id);
        restaurant.activate();
    }

    @Override
    @Transactional
    public void deactivate(Long id) {
        Restaurant restaurant = findById(id);
        restaurant.deactivate();
    }

    @Override
    @Transactional
    public void associatePaymentMethod(Long restaurantId, Long paymentMethodId) {
        Restaurant restaurant = findById(restaurantId);
        restaurant.associatePaymentMethod(paymentMethodService.findById(paymentMethodId));
    }

    @Override
    @Transactional
    public void disassociatePaymentMethod(Long restaurantId, Long paymentMethodId) {
        Restaurant restaurant = findById(restaurantId);
        restaurant.disassociatePaymentMethod(paymentMethodService.findById(paymentMethodId));
    }

    @Override
    @Transactional
    public void open(Long id) {
        Restaurant restaurant = findById(id);
        restaurant.open();
    }

    @Override
    @Transactional
    public void close(Long id) {
        Restaurant restaurant = findById(id);
        restaurant.close();
    }
}
