package com.kitchen.iChef.Controller.Model.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RecipeResponse {
    private String recipeId;
    private String title;
    private String steps;
    private Float rating;
    private Float difficulty;
    private Integer preparationTime;
    private Integer portions;
    private String notes;
    private Integer numberOfViews;
    private String imagePath;
    private String userId;
    private List<RecipeIngredientResponse> recipeIngredientList;
    private List<RecipeUtensilResponse> recipeUtensilList;
}
