package com.kitchen.iChef.Repository;

import com.kitchen.iChef.Domain.Recipe;
import com.kitchen.iChef.Domain.RecipeUtensil;
import com.kitchen.iChef.Repository.Interfaces.ICrudRepository;
import com.kitchen.iChef.Repository.Interfaces.IRecipeUtensilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class RecipeUtensilRepository implements ICrudRepository<RecipeUtensil, String> {
    private final IRecipeUtensilRepository iRecipeUtensilRepository;

    @Autowired
    public RecipeUtensilRepository(IRecipeUtensilRepository iRecipeUtensilRepository) {
        this.iRecipeUtensilRepository = iRecipeUtensilRepository;
    }


    @Override
    public RecipeUtensil findOne(String s) {
        if (iRecipeUtensilRepository.findById(s).isPresent()) {
            return iRecipeUtensilRepository.findById(s).get();
        }
        return null;
    }

    @Override
    public List<RecipeUtensil> findAll() {
        List<RecipeUtensil> allRecipesUtensils = new ArrayList<>();
        Iterable<RecipeUtensil> recipeUtensils = iRecipeUtensilRepository.findAll();
        for (RecipeUtensil ru : recipeUtensils) {
            allRecipesUtensils.add(ru);
        }
        return allRecipesUtensils;
    }

    @Override
    public RecipeUtensil save(RecipeUtensil entity) {
        return iRecipeUtensilRepository.save(entity);
    }

    @Override
    public RecipeUtensil delete(String s) {
        RecipeUtensil recipeUtensil = findOne(s);
        iRecipeUtensilRepository.deleteById(s);
        return recipeUtensil;
    }

    @Override
    public RecipeUtensil update(RecipeUtensil entity) {
        RecipeUtensil recipeUtensilInDb = findOne(entity.getRecipeUtensilId());
        recipeUtensilInDb.setUtensil(entity.getUtensil());
        recipeUtensilInDb.setRecipe(entity.getRecipe());
        iRecipeUtensilRepository.save(recipeUtensilInDb);
        return recipeUtensilInDb;
    }

    public List<RecipeUtensil> findRecipeUtensilsByRecipe(Recipe recipe) {
        return StreamSupport.stream(iRecipeUtensilRepository.findRecipeUtensilsByRecipe(recipe).spliterator(), false)
                .collect(Collectors.toList());
    }
}
