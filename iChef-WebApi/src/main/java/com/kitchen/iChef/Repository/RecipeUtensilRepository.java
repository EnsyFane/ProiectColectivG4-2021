package com.kitchen.iChef.Repository;

import com.kitchen.iChef.Domain.RecipeUtensil;
import com.kitchen.iChef.Repository.Interfaces.ICrudRepository;
import com.kitchen.iChef.Repository.Interfaces.IRecipeUtensilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
        iRecipeUtensilRepository.deleteById(entity.getRecipeUtensilId());
        iRecipeUtensilRepository.save(entity);
        return entity;
    }
}
