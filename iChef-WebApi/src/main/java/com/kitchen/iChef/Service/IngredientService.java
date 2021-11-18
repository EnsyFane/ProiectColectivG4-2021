package com.kitchen.iChef.Service;

import com.kitchen.iChef.Domain.Ingredient;
import com.kitchen.iChef.Exceptions.ResourceNotFoundException;
import com.kitchen.iChef.Exceptions.ValidationException;
import com.kitchen.iChef.Repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient addIngredient(Ingredient ingredient) throws ValidationException {

        if (ingredientRepository.findByName(ingredient.getName()).isPresent()) {
            throw new ValidationException("Ingredient already exists!");
        }
        return ingredientRepository.save(ingredient);
    }

    public Ingredient getIngredient(String id) {
        Ingredient ingredient;
        try {
            ingredient = ingredientRepository.findOne(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("No ingredient with this id");
        }
        return ingredient;
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient deleteIngredient(String id) {
        Ingredient ingredient;
        try {
            ingredient = ingredientRepository.delete(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("No ingredient with this id");
        }
        return ingredient;
    }

    public Ingredient updateIngredient(Ingredient ingredient) {
        Ingredient ingredientModified;
        try {
            ingredientModified = ingredientRepository.update(ingredient);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("No ingredient with this id");
        }
        return ingredientModified;
    }

}
