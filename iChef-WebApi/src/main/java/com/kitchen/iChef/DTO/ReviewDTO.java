package com.kitchen.iChef.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class ReviewDTO {

    @NotBlank(message = "The recipeId is not valid!")
    private String recipeId;

    @NotBlank(message = "The userId is not valid!")
    private String userId;

    @Min(value = 1, message = "The rating of the recipe is not valid!")
    @Max(value = 5, message = "The rating of the recipe is not valid!")
    @NotBlank(message = "The rating of the recipe is not valid!")
    private int rating;

    @NotBlank(message = "The comment is not valid!")
    private String comment;
}
