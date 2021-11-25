package com.kitchen.iChef.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecipeIngredientDTO {
    private String ingredientName;
    private int amount;
    private String measurementUnit;
}
