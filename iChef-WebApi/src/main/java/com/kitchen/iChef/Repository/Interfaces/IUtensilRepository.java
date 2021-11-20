package com.kitchen.iChef.Repository.Interfaces;

import com.kitchen.iChef.Domain.Utensil;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUtensilRepository extends CrudRepository<Utensil, String> {
    @Query(value = "SELECT * FROM utensil WHERE name = :name", nativeQuery = true)
    Optional<Utensil> findByName(@Param("name") String name);
}
