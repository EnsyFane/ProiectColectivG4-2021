package com.kitchen.iChef.Controller.Model.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class IngredientRequest {
    @NotBlank(message = "The name of the recipe is not valid!")
    private String name;
}