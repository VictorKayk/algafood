package com.victorkayk.algafood.domain.repository;

import com.victorkayk.algafood.domain.model.PaymentMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    @Query(
            value = "SELECT pm.* FROM restaurants r RIGHT JOIN restaurant_payment_methods rpm ON r.id = rpm.restaurant_id RIGHT JOIN payment_methods pm ON rpm.payment_method_id = pm.id WHERE r.id = :restaurantId",
            nativeQuery = true
    )
    Page<PaymentMethod> findAllByRestaurantId(Pageable pageable, @Param("restaurantId") Long restaurantId);
}
