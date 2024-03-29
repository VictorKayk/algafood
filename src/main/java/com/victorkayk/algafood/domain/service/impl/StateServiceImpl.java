package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.enums.ErrorEnum;
import com.victorkayk.algafood.domain.exception.ApiException;
import com.victorkayk.algafood.domain.model.State;
import com.victorkayk.algafood.domain.repository.StateRepository;
import com.victorkayk.algafood.domain.service.StateService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StateServiceImpl implements StateService {
    @Autowired
    private StateRepository stateRepository;

    @Override
    @Transactional
    public State save(State state) {
        return stateRepository.save(state);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        State state = findById(id);

        try {
            stateRepository.delete(state);
            stateRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ApiException(ErrorEnum.STATE_IN_USE);
        }
    }

    @Override
    public Page<State> findAll(Pageable pageable) {
        return stateRepository.findAll(pageable);
    }

    @Override
    public State findById(Long id) {
        return stateRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorEnum.STATE_NOT_FOUND));
    }

    @Override
    @Transactional
    public State update(Long id, State state) {
        State savedState = findById(id);
        BeanUtils.copyProperties(state, savedState, "id");
        return stateRepository.save(savedState);
    }
}
