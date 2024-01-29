package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.enums.ErrorEnum;
import com.victorkayk.algafood.domain.exception.ApiException;
import com.victorkayk.algafood.domain.model.City;
import com.victorkayk.algafood.domain.repository.CityRepository;
import com.victorkayk.algafood.domain.service.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void delete(Long id) {
        City city = findById(id);
        cityRepository.delete(city);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorEnum.CITY_NOT_FOUND));
    }

    @Override
    public City update(Long id, City city) {
        City savedCity = findById(id);
        BeanUtils.copyProperties(city, savedCity, "id");
        return save(savedCity);
    }
}
