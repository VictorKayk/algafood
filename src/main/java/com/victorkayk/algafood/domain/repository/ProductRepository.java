package com.victorkayk.algafood.domain.repository;

import com.victorkayk.algafood.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.restaurant.id = :restaurantId")
    List<Product> findAll(@Param("restaurantId") Long restaurantId);

    @Query("SELECT p FROM Product p WHERE p.restaurant.id = :restaurantId AND p.id = :id")
    Optional<Product> findById(@Param("restaurantId") Long restaurantId, @Param("id") Long id);

    @Modifying
    @Query("DELETE FROM Product p WHERE p.restaurant.id = :restaurantId AND p.id = :id")
    void delete(@Param("restaurantId") Long restaurantId, @Param("id") Long id);
}
