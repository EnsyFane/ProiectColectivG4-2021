package com.kitchen.iChef.Controller.Model.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class RecipeUtensilRequest {
    @NotBlank(message = "The utensil name is not valid!")
    private String utensilName;
}
