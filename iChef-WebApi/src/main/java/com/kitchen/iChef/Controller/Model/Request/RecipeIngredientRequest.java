package com.kitchen.iChef.Controller.Model.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class RecipeIngredientRequest {

    @NotBlank(message = "The ingredient name is not valid!")
    private String ingredientName;

    @Min(value = 1, message = "The ingredient amount is not valid!")
    private int amount;
}