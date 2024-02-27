package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StateService {
    State save(State state);

    void delete(Long id);

    Page<State> findAll(Pageable pageable);

    State findById(Long id);

    State update(Long id, State state);
}
