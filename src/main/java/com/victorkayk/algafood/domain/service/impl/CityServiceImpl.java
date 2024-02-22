package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.enums.ErrorEnum;
import com.victorkayk.algafood.domain.exception.ApiException;
import com.victorkayk.algafood.domain.model.City;
import com.victorkayk.algafood.domain.model.State;
import com.victorkayk.algafood.domain.repository.CityRepository;
import com.victorkayk.algafood.domain.service.CityService;
import com.victorkayk.algafood.domain.service.StateService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateService stateService;

    @Override
    @Transactional
    public City save(City city) {
        State state = stateService.findById(city.getState().getId());
        city.setState(state);
        return cityRepository.save(city);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        City city = findById(id);

        try {
            cityRepository.delete(city);
            cityRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ApiException(ErrorEnum.CITY_IN_USE);
        }
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
    @Transactional
    public City update(Long id, City city) {
        City savedCity = findById(id);
        State state = stateService.findById(city.getState().getId());

        city.setState(state);

        BeanUtils.copyProperties(city, savedCity, "id");

        return save(savedCity);
    }
}
