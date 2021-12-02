package com.kitchen.iChef.Controller.Model.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Getter
@Setter
@ToString
public class RecipeIngredientRequest {

    @NotBlank(message = "The ingredient name is not valid!")
    private String ingredientName;

    @Min(value = 1, message = "The ingredient amount is not valid!")
    private int amount;

    private String measurementUnit;
}