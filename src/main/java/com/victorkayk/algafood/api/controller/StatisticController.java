package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.domain.dto.SalesDTO;
import com.victorkayk.algafood.domain.dto.SalesFilterDTO;
import com.victorkayk.algafood.domain.service.SalesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Statistics", description = "Statistics endpoints")
@RestController
@RequestMapping("/statistics")
public class StatisticController {
    @Autowired
    private SalesService salesService;

    @GetMapping("/sales")
    public List<SalesDTO> listSales(SalesFilterDTO dto) {
        return salesService.listSales(dto);
    }
}
