package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.City;

import java.util.List;

public interface CityService {
    City save(City city);

    void delete(Long id);

    List<City> findAll();

    City findById(Long id);

    City update(Long id, City city);
}
