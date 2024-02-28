package com.victorkayk.algafood.domain.repository;

import com.victorkayk.algafood.domain.dto.SalesDTO;
import com.victorkayk.algafood.domain.dto.SalesFilterDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository {
    List<SalesDTO> list(SalesFilterDTO dto);
}
