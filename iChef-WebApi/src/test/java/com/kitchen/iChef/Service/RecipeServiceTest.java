package com.kitchen.iChef.Service;

import com.kitchen.iChef.Controller.RecipeController;
import com.kitchen.iChef.DTO.RecipeDTO;
import com.kitchen.iChef.Domain.Recipe;
import com.kitchen.iChef.Repository.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipeService recipeService;

    private Recipe createRecipe(String recipeId, String title, String steps, Float rating, Float difficulty, Integer preparationTime,
                              Integer portions, String notes, Integer numberOfViews, String imagePath)
    {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(recipeId);
        recipe.setTitle(title);
        recipe.setSteps(steps);
        recipe.setRating(rating);
        recipe.setDifficulty(difficulty);
        recipe.setDifficulty(difficulty);
        recipe.setPreparationTime(preparationTime);
        recipe.setPortions(portions);
        recipe.setNotes(notes);
        recipe.setNumberOfViews(numberOfViews);
        recipe.setImagePath(imagePath);
        return recipe;
    }

    @Test
    void getAllRecipes_success() {
        Recipe recipe1 = createRecipe("1","Pizza","1,2,3",9.6f,4.5f,30,4,"no notes",
                100,"asd.png");
        Recipe recipe2 = createRecipe("2","Burger","1,2,3",9.6f,4.5f,30,4,"no notes",
                100,"asd.png");
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe1);
        recipes.add(recipe2);
        Mockito.when(recipeRepository.findAll()).thenReturn(recipes);
        List<RecipeDTO> resultedRecipes = recipeService.getAllRecipes();
        Assertions.assertEquals(2,resultedRecipes.size());
    }

    @Test
    void getRecipe() {
    }

    @Test
    void getAllUserRecipes() {
    }

    @Test
    void addRecipe_success() {

    }

    @Test
    void addRecipe_failure() {
    }

    @Test
    void updateRecipe() {
    }

    @Test
    void deleteRecipe() {
    }
}