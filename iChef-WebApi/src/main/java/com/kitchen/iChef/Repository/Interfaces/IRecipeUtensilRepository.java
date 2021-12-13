package com.kitchen.iChef.Repository.Interfaces;

import com.kitchen.iChef.Domain.Recipe;
import com.kitchen.iChef.Domain.RecipeUtensil;
import org.springframework.data.repository.CrudRepository;

public interface IRecipeUtensilRepository extends CrudRepository<RecipeUtensil, String> {
    Iterable<RecipeUtensil> findRecipeUtensilsByRecipe(Recipe recipe);
}
