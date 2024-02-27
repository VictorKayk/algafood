package com.victorkayk.algafood.domain.repository;

import com.victorkayk.algafood.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    Page<Order> findAll(Specification<Order> spec, Pageable pageable);

    @Query("SELECT o FROM Order o JOIN FETCH o.items i JOIN FETCH i.product JOIN FETCH o.client JOIN FETCH o.paymentMethod JOIN FETCH o.restaurant r JOIN FETCH r.kitchen JOIN FETCH o.address.city c  JOIN FETCH c.state WHERE o.uuid = :uuid")
    Optional<Order> findByUuid(@Param("uuid") String uuid);

    @Modifying
    @Query("DELETE FROM OrderItem oi WHERE oi.order.uuid = :uuid")
    void deleteOrderItems(@Param("uuid") String uuid);
}
