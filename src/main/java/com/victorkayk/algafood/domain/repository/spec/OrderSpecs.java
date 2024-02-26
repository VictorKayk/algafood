package com.victorkayk.algafood.domain.repository.spec;

import com.victorkayk.algafood.domain.dto.OrderFilterDTO;
import com.victorkayk.algafood.domain.model.Order;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class OrderSpecs {
    public static Specification<Order> filterOrders(OrderFilterDTO dto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            root.fetch("restaurant").fetch("kitchen");
            root.fetch("client");

            if (dto != null) {
                if (dto.restaurantId() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("restaurant").get("id"), dto.restaurantId()));
                }

                if (dto.clientId() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("client").get("id"), dto.clientId()));
                }

                if (dto.status() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("status"), dto.status().getValue()));
                }

                ZoneOffset zoneOffset = ZoneOffset.UTC;
                if (dto.startDate() != null) {
                    OffsetDateTime date = dto.startDate().atStartOfDay().atOffset(zoneOffset);
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), date));
                }

                if (dto.endDate() != null) {
                    OffsetDateTime date = dto.endDate().plusDays(1).atStartOfDay().atOffset(zoneOffset);
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), date));
                }
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
