package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {
    City save(City city);

    void delete(Long id);

    Page<City> findAll(Pageable pageable);

    City findById(Long id);

    City update(Long id, City city);
}
