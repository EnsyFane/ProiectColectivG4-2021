package com.kitchen.iChef.Repository.Interfaces;

import com.kitchen.iChef.Domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface IRecipeRepository extends CrudRepository<Recipe, String> {
    Iterable<Recipe> findRecipesByTitleContains(String string);
    Iterable<Recipe> findRecipesByTitleContainsAndDifficultyLessThanEqualAndPreparationTimeLessThanEqualAndPortionsEquals(String title,Float difficulty,Integer preparationTime,Integer portions);
    Iterable<Recipe> findRecipesByTitleContainsAndDifficultyGreaterThanEqualAndPreparationTimeLessThanEqualAndPortionsEquals(String title,Float difficulty,Integer preparationTime,Integer portions);
    Iterable<Recipe> findRecipesByTitleContainsAndDifficultyEqualsAndPreparationTimeLessThanAndPortionsEquals(String title,Float difficulty,Integer preparationTime,Integer portions);
    Iterable<Recipe> findRecipesByTitleContainsAndDifficultyLessThanEqualAndPreparationTimeLessThanEqualAndPortionsLessThanEqual(String title,Float difficulty,Integer preparationTime,Integer portions);
    Iterable<Recipe> findRecipesByTitleContainsAndDifficultyLessThanEqualAndPreparationTimeLessThanEqualAndPortionsGreaterThanEqual(String title,Float difficulty,Integer preparationTime,Integer portions);
    Iterable<Recipe> findRecipesByTitleContainsAndDifficultyGreaterThanEqualAndPreparationTimeLessThanEqualAndPortionsGreaterThanEqual(String title,Float difficulty,Integer preparationTime,Integer portions);
    Iterable<Recipe> findRecipesByTitleContainsAndDifficultyGreaterThanEqualAndPreparationTimeLessThanEqualAndPortionsLessThanEqual(String title,Float difficulty,Integer preparationTime,Integer portions);
    Iterable<Recipe> findRecipesByTitleContainsAndDifficultyEqualsAndPreparationTimeLessThanEqualAndPortionsLessThanEqual(String title,Float difficulty,Integer preparationTime,Integer portions);
    Iterable<Recipe> findRecipesByTitleContainsAndDifficultyEqualsAndPreparationTimeLessThanEqualAndPortionsGreaterThanEqual(String title,Float difficulty,Integer preparationTime,Integer portions);


}
