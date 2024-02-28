package com.victorkayk.algafood.domain.repository.impl;

import com.victorkayk.algafood.domain.dto.SalesDTO;
import com.victorkayk.algafood.domain.dto.SalesFilterDTO;
import com.victorkayk.algafood.domain.enums.PeriodEnum;
import com.victorkayk.algafood.domain.enums.StatusOrderEnum;
import com.victorkayk.algafood.domain.model.Order;
import com.victorkayk.algafood.domain.repository.SalesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SalesRepositoryImpl implements SalesRepository {
    private static final String DATE_FORMAT_DAY = "%Y-%m-%d";
    private static final String DATE_FORMAT_MONTH = "%Y-%m";
    private static final String DATE_FORMAT_YEAR = "%Y";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SalesDTO> list(SalesFilterDTO dto) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SalesDTO> query = builder.createQuery(SalesDTO.class);
        Root<Order> root = query.from(Order.class);

        List<Predicate> predicates = getPredicates(dto, builder, root);
        query.where(predicates.toArray(Predicate[]::new));

        PeriodEnum period = dto.period() != null ? dto.period() : PeriodEnum.MONTH;
        Expression<String> periodDate = getPeriodDate(period, builder, root);

        CompoundSelection<SalesDTO> selection = builder.construct(
                SalesDTO.class,
                builder.count(root.get("id")),
                builder.sum(root.get("total")),
                periodDate
        );

        query.select(selection);
        query.orderBy(builder.asc(periodDate));
        query.groupBy(periodDate);

        TypedQuery<SalesDTO> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    private List<Predicate> getPredicates(SalesFilterDTO dto, CriteriaBuilder builder, Root<Order> root) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.in(root.get("status")).value(Arrays.asList(StatusOrderEnum.CONFIRMED, StatusOrderEnum.DELIVERED)));

        if (dto.restaurantId() != null) {
            predicates.add(builder.equal(root.get("restaurant").get("id"), dto.restaurantId()));
        }

        ZoneOffset zoneOffset = ZoneOffset.UTC;
        if (dto.startDate() != null) {
            OffsetDateTime date = dto.startDate().atStartOfDay().atOffset(zoneOffset);
            predicates.add(builder.greaterThanOrEqualTo(root.get("createdAt"), date));
        }

        if (dto.endDate() != null) {
            OffsetDateTime date = dto.endDate().plusDays(1).atStartOfDay().atOffset(zoneOffset);
            predicates.add(builder.lessThanOrEqualTo(root.get("createdAt"), date));
        }

        return predicates;
    }

    private Expression<String> getPeriodDate(PeriodEnum period, CriteriaBuilder builder, Root<?> root) {
        String dateFormat = switch (period) {
            case DAY -> DATE_FORMAT_DAY;
            case YEAR -> DATE_FORMAT_YEAR;
            default -> DATE_FORMAT_MONTH;
        };

        return builder.function("DATE_FORMAT", String.class, root.get("createdAt"), builder.literal(dateFormat));
    }
}
