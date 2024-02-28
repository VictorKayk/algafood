package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.dto.SalesDTO;
import com.victorkayk.algafood.domain.dto.SalesFilterDTO;
import com.victorkayk.algafood.domain.repository.SalesRepository;
import com.victorkayk.algafood.domain.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {
    @Autowired
    private SalesRepository salesRepository;

    @Override
    public List<SalesDTO> listSales(SalesFilterDTO dto) {
        return salesRepository.list(dto);
    }
}
