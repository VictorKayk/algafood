package com.victorkayk.algafood.domain.repository;

import com.victorkayk.algafood.domain.model.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    List<Order> findAll(Specification<Order> spec);

    @Query("select o from Order o join fetch o.items i join fetch i.product join fetch o.client join fetch o.paymentMethod join fetch o.restaurant r join fetch r.kitchen join fetch o.address.city c  join fetch c.state where o.uuid = :uuid")
    Optional<Order> findByUuid(String uuid);

    @Modifying
    @Query("delete from OrderItem oi where oi.order.uuid = :uuid")
    void deleteOrderItems(@Param("uuid") String uuid);
}
