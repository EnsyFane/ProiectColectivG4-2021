package com.kitchen.iChef.Repository;

import com.kitchen.iChef.Domain.Ingredient;
import com.kitchen.iChef.Repository.Interfaces.ICrudRepository;
import com.kitchen.iChef.Repository.Interfaces.IIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository

public class IngredientRepository implements ICrudRepository<Ingredient, String> {
    private final IIngredientRepository iIngredientRepository;

    @Autowired
    public IngredientRepository(IIngredientRepository iIngredientRepository) {
        this.iIngredientRepository = iIngredientRepository;
    }

    @Override
    public Ingredient findOne(String s) {
        if (iIngredientRepository.findById(s).isPresent()) {
            return iIngredientRepository.findById(s).get();
        }
        return null;
    }

    @Override
    public List<Ingredient> findAll() {
        List<Ingredient> allIngredients = new ArrayList<>();
        Iterable<Ingredient> ingredients = iIngredientRepository.findAll();
        for (Ingredient ingredient : ingredients) {
            allIngredients.add(ingredient);
        }
        return allIngredients;
    }

    @Override
    public Ingredient save(Ingredient entity) {
        return iIngredientRepository.save(entity);
    }

    @Override
    public Ingredient delete(String s) {
        Ingredient ingredient = findOne(s);
        iIngredientRepository.deleteById(s);
        return ingredient;
    }

    @Override
    public Ingredient update(Ingredient entity) {
        iIngredientRepository.deleteById(entity.getIngredientId());
        iIngredientRepository.save(entity);
        return entity;
    }

    public Optional<Ingredient> findByName(String name) {
        return iIngredientRepository.findByName(name);
    }

}