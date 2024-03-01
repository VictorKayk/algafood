package com.victorkayk.algafood.domain.repository;

import com.victorkayk.algafood.domain.model.Template;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemplateRepository extends CrudRepository<Template, String> {
    @Query(
            "SELECT et FROM Template et WHERE et.name = :name"
    )
    Optional<Template> findByName(@Param("name") String name);

    @Query(
            "SELECT et FROM Template et WHERE et.name IN :names"
    )
    List<Template> findByNames(@Param("names") List<String> names);
}
