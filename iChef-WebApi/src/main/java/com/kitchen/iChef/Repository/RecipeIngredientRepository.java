package com.kitchen.iChef.Repository;

import com.kitchen.iChef.Domain.Recipe;
import com.kitchen.iChef.Domain.RecipeIngredient;
import com.kitchen.iChef.Repository.Interfaces.ICrudRepository;
import com.kitchen.iChef.Repository.Interfaces.IRecipeIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class RecipeIngredientRepository implements ICrudRepository<RecipeIngredient, String> {
    private final IRecipeIngredientRepository iRecipeIngredientRepository;

    @Autowired
    public RecipeIngredientRepository(IRecipeIngredientRepository iRecipeIngredientRepository) {
        this.iRecipeIngredientRepository = iRecipeIngredientRepository;
    }


    @Override
    public RecipeIngredient findOne(String s) {
        if (iRecipeIngredientRepository.findById(s).isPresent()) {
            return iRecipeIngredientRepository.findById(s).get();
        }
        return null;
    }

    @Override
    public List<RecipeIngredient> findAll() {
        List<RecipeIngredient> allRecipes = new ArrayList<>();
        Iterable<RecipeIngredient> recipes = iRecipeIngredientRepository.findAll();
        for (RecipeIngredient recipe : recipes) {
            allRecipes.add(recipe);
        }
        return allRecipes;
    }

    @Override
    public RecipeIngredient save(RecipeIngredient entity) {
        return iRecipeIngredientRepository.save(entity);
    }

    @Override
    public RecipeIngredient delete(String s) {
        RecipeIngredient recipeIngredient = findOne(s);
        iRecipeIngredientRepository.deleteById(s);
        return recipeIngredient;
    }

    @Override
    public RecipeIngredient update(RecipeIngredient entity) {
        RecipeIngredient recipeIngredientInDb = findOne(entity.getRecipeIngredientId());
        recipeIngredientInDb.setMeasurementUnit(entity.getMeasurementUnit());
        recipeIngredientInDb.setIngredient(entity.getIngredient());
        recipeIngredientInDb.setAmount(entity.getAmount());
        recipeIngredientInDb.setRecipe(entity.getRecipe());
        iRecipeIngredientRepository.save(recipeIngredientInDb);
        return recipeIngredientInDb;
    }

    public List<RecipeIngredient> findRecipeIngredientsByRecipe(Recipe recipe) {
        return StreamSupport.stream(iRecipeIngredientRepository.findRecipeIngredientsByRecipe(recipe).spliterator(), false)
                .collect(Collectors.toList());
    }

}
