package com.victorkayk.algafood.domain.repository;

import com.victorkayk.algafood.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Modifying
    @Query("delete from OrderItem oi where oi.order.id = :id")
    void deleteOrderItems(@Param("id") Long id);
}
