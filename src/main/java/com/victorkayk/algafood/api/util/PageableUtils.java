package com.victorkayk.algafood.api.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public class PageableUtils {
    public static Pageable mapSort(Pageable pageable, Map<String, String> possibleSorts) {
        List<Sort.Order> orders = pageable.getSort().stream()
                .filter(order -> possibleSorts.containsKey(order.getProperty()))
                .map(order -> new Sort.Order(
                        order.getDirection(),
                        possibleSorts.get(order.getProperty())
                ))
                .toList();

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
    }
}
