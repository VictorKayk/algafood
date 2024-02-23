package com.victorkayk.algafood.domain.repository;

import com.victorkayk.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Modifying
    @Query("update Restaurant r set r.isActive = :isActive where r.id in :ids")
    void updateIsActive(@Param("ids") List<Long> ids, @Param("isActive") Boolean isActive);
}
