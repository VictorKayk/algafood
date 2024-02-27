package com.victorkayk.algafood.domain.repository;

import com.victorkayk.algafood.domain.model.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    @Query(
            value = "SELECT p.* FROM `groups` g RIGHT JOIN groups_permissions gp ON g.id = gp.group_id RIGHT JOIN permissions p on gp.permission_id = p.id WHERE g.id = :groupId",
            nativeQuery = true
    )
    Page<Permission> findPermissionsByGroupId(Pageable pageable, @Param("groupId") Long groupId);
}
