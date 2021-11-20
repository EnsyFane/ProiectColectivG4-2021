package com.kitchen.iChef.Repository.Interfaces;

import com.kitchen.iChef.Domain.RecipeIngredient;
import org.springframework.data.repository.CrudRepository;

public interface IRecipeIngredientRepository extends CrudRepository<RecipeIngredient, String> {
}
