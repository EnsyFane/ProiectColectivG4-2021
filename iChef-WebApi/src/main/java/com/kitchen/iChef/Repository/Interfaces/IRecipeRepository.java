package com.kitchen.iChef.Repository.Interfaces;

import com.kitchen.iChef.Domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface IRecipeRepository extends CrudRepository<Recipe, String> {
}
