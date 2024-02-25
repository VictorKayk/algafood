package com.victorkayk.algafood.domain.repository;

import com.victorkayk.algafood.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o join fetch o.client join fetch o.restaurant r join fetch r.kitchen")
    List<Order> findAll();

    @Query("select o from Order o join fetch o.items i join fetch i.product join fetch o.client join fetch o.paymentMethod join fetch o.restaurant r join fetch r.kitchen join fetch o.address.city c  join fetch c.state where o.id = :id")
    Optional<Order> findById(@Param("id") Long id);

    @Modifying
    @Query("delete from OrderItem oi where oi.order.id = :id")
    void deleteOrderItems(@Param("id") Long id);
}
