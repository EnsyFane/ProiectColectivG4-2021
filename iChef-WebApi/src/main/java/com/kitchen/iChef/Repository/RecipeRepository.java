package com.kitchen.iChef.Repository;

import com.kitchen.iChef.Domain.Recipe;
import com.kitchen.iChef.Repository.Interfaces.ICrudRepository;
import com.kitchen.iChef.Repository.Interfaces.IRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecipeRepository implements ICrudRepository<Recipe, String> {
    private final IRecipeRepository iRecipeRepository;

    @Autowired
    public RecipeRepository(IRecipeRepository iRecipeRepository) {
        this.iRecipeRepository = iRecipeRepository;
    }

    @Override
    public Recipe findOne(String s) {
        if (iRecipeRepository.findById(s).isPresent()) {
            return iRecipeRepository.findById(s).get();
        }
        return null;
    }
    public List<Recipe> findRecipesByTitle(String title)
    {
        List<Recipe> filteredRecipes = new ArrayList<>();
        Iterable<Recipe> recipes = iRecipeRepository.findRecipesByTitleContains(title);
        for (Recipe recipe : recipes) {
            filteredRecipes.add(recipe);
        }
        return filteredRecipes;
    }
    public List<Recipe> findRecipesByTitleContainsAndDifficultyLessThanEqualAndPreparationTimeLessThanEqualAndPortionsEquals(String title,Float difficulty,Integer preparationTime,Integer portions)
    {
        List<Recipe> filteredRecipes = new ArrayList<>();
        Iterable<Recipe> recipes = iRecipeRepository.findRecipesByTitleContainsAndDifficultyLessThanEqualAndPreparationTimeLessThanEqualAndPortionsEquals(title,difficulty,preparationTime, portions);
        for (Recipe recipe : recipes) {
            filteredRecipes.add(recipe);
        }
        return filteredRecipes;
    }
    public List<Recipe> findRecipesByTitleContainsAndDifficultyLessThanEqualAndPreparationTimeLessThanEqualAndPortionsLessThanEqual(String title,Float difficulty,Integer preparationTime,Integer portions)
    {
        List<Recipe> filteredRecipes = new ArrayList<>();
        Iterable<Recipe> recipes = iRecipeRepository.findRecipesByTitleContainsAndDifficultyLessThanEqualAndPreparationTimeLessThanEqualAndPortionsLessThanEqual(title,difficulty,preparationTime, portions);
        for (Recipe recipe : recipes) {
            filteredRecipes.add(recipe);
        }
        return filteredRecipes;
    }
    public List<Recipe> findRecipesByTitleContainsAndDifficultyLessThanEqualAndPreparationTimeLessThanEqualAndPortionsGreaterThanEqual(String title,Float difficulty,Integer preparationTime,Integer portions)
    {
        List<Recipe> filteredRecipes = new ArrayList<>();
        Iterable<Recipe> recipes = iRecipeRepository.findRecipesByTitleContainsAndDifficultyLessThanEqualAndPreparationTimeLessThanEqualAndPortionsGreaterThanEqual(title,difficulty,preparationTime, portions);
        for (Recipe recipe : recipes) {
            filteredRecipes.add(recipe);
        }
        return filteredRecipes;
    }
    public List<Recipe> findRecipesByTitleContainsAndDifficultyGreaterThanEqualAndPreparationTimeLessThanEqualAndPortionsEquals(String title,Float difficulty,Integer preparationTime,Integer portions)
    {
        List<Recipe> filteredRecipes = new ArrayList<>();
        Iterable<Recipe> recipes = iRecipeRepository.findRecipesByTitleContainsAndDifficultyGreaterThanEqualAndPreparationTimeLessThanEqualAndPortionsEquals(title,difficulty,preparationTime, portions);
        for (Recipe recipe : recipes) {
            filteredRecipes.add(recipe);
        }
        return filteredRecipes;
    }
    public List<Recipe> findRecipesByTitleContainsAndDifficultyEqualsAndPreparationTimeLessThanEqualAndPortionsEquals(String title,Float difficulty,Integer preparationTime,Integer portions)
    {
        List<Recipe> filteredRecipes = new ArrayList<>();
        Iterable<Recipe> recipes = iRecipeRepository.findRecipesByTitleContainsAndDifficultyLessThanEqualAndPreparationTimeLessThanEqualAndPortionsEquals(title,difficulty,preparationTime, portions);
        for (Recipe recipe : recipes) {
            filteredRecipes.add(recipe);
        }
        return filteredRecipes;
    }

    public List<Recipe> findRecipesByTitleContainsAndDifficultyGreaterThanEqualAndPreparationTimeLessThanEqualAndPortionsGreaterThanEqual(String title,Float difficulty,Integer preparationTime,Integer portions)
    {
        List<Recipe> filteredRecipes = new ArrayList<>();
        Iterable<Recipe> recipes = iRecipeRepository.findRecipesByTitleContainsAndDifficultyGreaterThanEqualAndPreparationTimeLessThanEqualAndPortionsGreaterThanEqual(title,difficulty,preparationTime, portions);
        for (Recipe recipe : recipes) {
            filteredRecipes.add(recipe);
        }
        return filteredRecipes;
    }
    public List<Recipe> findRecipesByTitleContainsAndDifficultyGreaterThanEqualAndPreparationTimeLessThanEqualAndPortionsLessThanEqual(String title,Float difficulty,Integer preparationTime,Integer portions)
    {
        List<Recipe> filteredRecipes = new ArrayList<>();
        Iterable<Recipe> recipes = iRecipeRepository.findRecipesByTitleContainsAndDifficultyGreaterThanEqualAndPreparationTimeLessThanEqualAndPortionsLessThanEqual(title,difficulty,preparationTime, portions);
        for (Recipe recipe : recipes) {
            filteredRecipes.add(recipe);
        }
        return filteredRecipes;
    }
    public List<Recipe> findRecipesByTitleContainsAndDifficultyEqualsAndPreparationTimeLessThanAndPortionsEquals(String title,Float difficulty,Integer preparationTime,Integer portions)
    {
        List<Recipe> filteredRecipes = new ArrayList<>();
        Iterable<Recipe> recipes = iRecipeRepository.findRecipesByTitleContainsAndDifficultyEqualsAndPreparationTimeLessThanAndPortionsEquals(title,difficulty,preparationTime, portions);
        for (Recipe recipe : recipes) {
            filteredRecipes.add(recipe);
        }
        return filteredRecipes;
    }
    public List<Recipe> findRecipesByTitleContainsAndDifficultyEqualsAndPreparationTimeLessThanEqualAndPortionsLessThanEqual(String title,Float difficulty,Integer preparationTime,Integer portions)
    {
        List<Recipe> filteredRecipes = new ArrayList<>();
        Iterable<Recipe> recipes = iRecipeRepository.findRecipesByTitleContainsAndDifficultyEqualsAndPreparationTimeLessThanEqualAndPortionsLessThanEqual(title,difficulty,preparationTime, portions);
        for (Recipe recipe : recipes) {
            filteredRecipes.add(recipe);
        }
        return filteredRecipes;
    }
    public List<Recipe> findRecipesByTitleContainsAndDifficultyEqualsAndPreparationTimeLessThanEqualAndPortionsGreaterThanEqual(String title,Float difficulty,Integer preparationTime,Integer portions)
    {
        List<Recipe> filteredRecipes = new ArrayList<>();
        Iterable<Recipe> recipes = iRecipeRepository.findRecipesByTitleContainsAndDifficultyEqualsAndPreparationTimeLessThanEqualAndPortionsGreaterThanEqual(title,difficulty,preparationTime, portions);
        for (Recipe recipe : recipes) {
            filteredRecipes.add(recipe);
        }
        return filteredRecipes;
    }

    @Override
    public List<Recipe> findAll() {
        List<Recipe> allRecipes = new ArrayList<>();
        Iterable<Recipe> recipes = iRecipeRepository.findAll();
        for (Recipe recipe : recipes) {
            allRecipes.add(recipe);
        }
        return allRecipes;
    }

    @Override
    public Recipe save(Recipe entity) {
        return iRecipeRepository.save(entity);
    }

    @Override
    public Recipe delete(String s) {
        Recipe recipe = findOne(s);
        iRecipeRepository.deleteById(s);
        return recipe;
    }

    @Override
    public Recipe update(Recipe entity) {
        iRecipeRepository.deleteById(entity.getRecipeId());
        iRecipeRepository.save(entity);
        return entity;
    }
}
