package com.kitchen.iChef.Mapper;

import com.kitchen.iChef.Controller.Model.Request.IngredientRequest;
import com.kitchen.iChef.Controller.Model.Response.IngredientResponse;
import com.kitchen.iChef.Domain.Ingredient;

public class IngredientMapper {
    public Ingredient mapFromRequest(IngredientRequest ingredientRequest) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientRequest.getName().toLowerCase());
        return ingredient;
    }

    public IngredientResponse mapToResponse(Ingredient ingredient) {
        IngredientResponse ingredientResponse = new IngredientResponse();
        ingredientResponse.setName(ingredient.getName());
        return ingredientResponse;
    }
}
