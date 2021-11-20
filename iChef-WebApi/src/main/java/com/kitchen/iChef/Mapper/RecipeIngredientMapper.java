package com.kitchen.iChef.Mapper;

import com.kitchen.iChef.Controller.Model.Request.RecipeIngredientRequest;
import com.kitchen.iChef.Controller.Model.Response.RecipeIngredientResponse;
import com.kitchen.iChef.DTO.RecipeIngredientDTO;

public class RecipeIngredientMapper {
    public RecipeIngredientDTO mapFromRequest(RecipeIngredientRequest ingredientRequest) {
        RecipeIngredientDTO recipeIngredientDTO = new RecipeIngredientDTO();
        recipeIngredientDTO.setAmount(ingredientRequest.getAmount());
        recipeIngredientDTO.setIngredientName(ingredientRequest.getIngredientName().toLowerCase());
        return recipeIngredientDTO;
    }

    public RecipeIngredientResponse mapToResponse(RecipeIngredientDTO ingredient) {
        RecipeIngredientResponse ingredientResponse = new RecipeIngredientResponse();
        ingredientResponse.setAmount(ingredient.getAmount());
        ingredientResponse.setIngredientName(ingredient.getIngredientName());
        return ingredientResponse;
    }
}
