package com.kitchen.iChef.Repository.Interfaces;

import com.kitchen.iChef.Domain.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IIngredientRepository extends CrudRepository<Ingredient, String> {
    @Query(value = "SELECT * FROM ingredient WHERE name = :name", nativeQuery = true)
    Optional<Ingredient> findByName(@Param("name") String name);
}

