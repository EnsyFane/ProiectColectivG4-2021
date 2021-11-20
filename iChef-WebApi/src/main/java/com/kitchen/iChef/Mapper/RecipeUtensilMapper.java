package com.kitchen.iChef.Mapper;

import com.kitchen.iChef.Controller.Model.Request.RecipeUtensilRequest;
import com.kitchen.iChef.Controller.Model.Response.RecipeUtensilResponse;
import com.kitchen.iChef.DTO.RecipeUtensilDTO;

public class RecipeUtensilMapper {
    public RecipeUtensilDTO mapFromRequest(RecipeUtensilRequest recipeUtensilRequest) {
        RecipeUtensilDTO recipeUtensilDTO = new RecipeUtensilDTO();
        recipeUtensilDTO.setUtensilName(recipeUtensilRequest.getUtensilName().toLowerCase());
        return recipeUtensilDTO;
    }

    public RecipeUtensilResponse mapToResponse(RecipeUtensilDTO recipeUtensilDTO) {
        RecipeUtensilResponse recipeUtensilResponse = new RecipeUtensilResponse();
        recipeUtensilResponse.setUtensilName(recipeUtensilDTO.getUtensilName());
        return recipeUtensilResponse;
    }
}
