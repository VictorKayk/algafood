package com.victorkayk.algafood.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class SalesDTO {
    private Long totalSales;
    private BigDecimal totalValue;
    private String salesAt;
}
