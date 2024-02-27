package com.victorkayk.algafood.domain.repository;

import com.victorkayk.algafood.domain.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query(
            value = "SELECT g.* FROM `groups` g WHERE g.id = :id",
            nativeQuery = true
    )
    Optional<Group> findById(Long id);

    @Query(
            value = "SELECT g.* FROM users u RIGHT JOIN user_groups ug ON u.id = ug.group_id RIGHT JOIN `groups` g on ug.group_id = g.id WHERE u.id = :userId",
            nativeQuery = true
    )
    Page<Group> findAllByUserId(Pageable pageable, @Param("userId") Long userId);
}
