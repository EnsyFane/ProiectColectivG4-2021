package com.kitchen.iChef.Repository.Interfaces;

import com.kitchen.iChef.Domain.Recipe;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface IRecipeRepository extends CrudRepository<Recipe, String> {
    Iterable<Recipe> findRecipesByTitleContains(String string);

    Iterable<Recipe> findAll(Sort sort);
}
