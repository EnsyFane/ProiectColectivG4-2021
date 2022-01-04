package com.kitchen.iChef.Mapper;

import com.kitchen.iChef.Controller.Model.Request.RecipeUtensilRequest;
import com.kitchen.iChef.Controller.Model.Response.RecipeUtensilResponse;
import com.kitchen.iChef.DTO.RecipeUtensilDTO;
import com.kitchen.iChef.DTO.ReviewDTO;
import com.kitchen.iChef.Domain.AppUser;
import com.kitchen.iChef.Domain.Recipe;
import com.kitchen.iChef.Domain.Review;

public class ReviewMapper {
    public Review mapFromRequest(ReviewDTO reviewDTO) {
        Review review = new Review();

        Recipe recipe = new Recipe();
        recipe.setRecipeId(reviewDTO.getRecipeId());
        review.setRecipe(recipe);

        AppUser user = new AppUser();
        user.setUserId(reviewDTO.getUserId());
        review.setUser(user);
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        return review;
    }

    public ReviewDTO mapToResponse(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setRecipeId(review.getRecipe().getRecipeId());
        reviewDTO.setUserId(review.getUser().getUserId());
        reviewDTO.setComment(review.getComment());
        reviewDTO.setRating(review.getRating());
        return reviewDTO;
    }
}
