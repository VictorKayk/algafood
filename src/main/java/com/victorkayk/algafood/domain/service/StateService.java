package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.State;

import java.util.List;

public interface StateService {
    State save(State state);

    void delete(Long id);

    List<State> findAll();

    State findById(Long id);

    State update(Long id, State state);
}
