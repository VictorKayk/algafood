package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.dto.SalesDTO;
import com.victorkayk.algafood.domain.dto.SalesFilterDTO;

import java.util.List;

public interface SalesService {
    List<SalesDTO> listSales(SalesFilterDTO dto);
}
