package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.model.State;
import com.victorkayk.algafood.domain.repository.StateRepository;
import com.victorkayk.algafood.domain.service.StateService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements StateService {
    @Autowired
    private StateRepository stateRepository;

    @Override
    public State save(State city) {
        return stateRepository.save(city);
    }

    @Override
    public void delete(Long id) {
        State state = findById(id);
        stateRepository.delete(state);
    }

    @Override
    public List<State> findAll() {
        return stateRepository.findAll();
    }

    @Override
    public State findById(Long id) {
        return stateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("State not found"));
    }

    @Override
    public State update(Long id, State city) {
        State savedState = findById(id);
        BeanUtils.copyProperties(city, savedState, "id");
        return save(savedState);
    }
}
