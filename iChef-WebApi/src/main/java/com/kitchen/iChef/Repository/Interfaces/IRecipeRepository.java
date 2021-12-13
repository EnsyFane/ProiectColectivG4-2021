package com.kitchen.iChef.Repository.Interfaces;

import com.kitchen.iChef.Domain.Recipe;
import com.kitchen.iChef.Domain.Token;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IRecipeRepository extends CrudRepository<Recipe, String> {

    List<Recipe> findBySteps(String query);
    Iterable<Recipe> findRecipesByTitleContains(String string);


}
