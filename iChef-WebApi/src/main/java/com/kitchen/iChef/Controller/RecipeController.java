package com.kitchen.iChef.Controller;

import com.kitchen.iChef.Controller.Model.Request.RecipeRequest;
import com.kitchen.iChef.Controller.Model.Request.SortingRequest;
import com.kitchen.iChef.Controller.Model.Request.UpdateRecipeRequest;
import com.kitchen.iChef.Controller.Model.Response.RecipeResponse;
import com.kitchen.iChef.DTO.UpdateRecipeDTO;
import com.kitchen.iChef.Mapper.RecipeIngredientMapper;
import com.kitchen.iChef.Mapper.RecipeMapper;
import com.kitchen.iChef.Mapper.RecipeUtensilMapper;
import com.kitchen.iChef.Mapper.UpdateRecipeMapper;
import com.kitchen.iChef.Repository.RecipeFilterCriteria;
import com.kitchen.iChef.Service.RecipeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;
    private final UpdateRecipeMapper updateRecipeMapper;
    private final RecipeIngredientMapper recipeIngredientMapper = new RecipeIngredientMapper();
    private final RecipeUtensilMapper recipeUtensilMapper = new RecipeUtensilMapper();

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
        this.recipeMapper = new RecipeMapper(recipeIngredientMapper, recipeUtensilMapper);
        this.updateRecipeMapper = new UpdateRecipeMapper(recipeIngredientMapper, recipeUtensilMapper);
    }

    @GetMapping
    public List<RecipeResponse> getAllRecipes() {
        return recipeService.getAllRecipes()
                .stream()
                .map(recipeMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public RecipeResponse getRecipeById(@Valid @PathVariable String id) {
        return recipeMapper.mapToResponse(recipeService.getRecipe(id));
    }

    @PostMapping
    public RecipeResponse addRecipe(@Valid @RequestBody RecipeRequest recipeRequest) {
        return recipeMapper.mapToResponse(recipeService.addRecipe(
                recipeMapper.mapFromRequest(recipeRequest)));
    }

    @PostMapping(value = "/sort")
    public List<RecipeResponse> sortRecipes(@Valid @RequestBody SortingRequest sortingRequest) {
        return recipeService.sortRecipes(sortingRequest.getField(), sortingRequest.isAscending())
                .stream()
                .map(recipeMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "/{id}")
    public RecipeResponse deleteRecipe(@PathVariable String id) {
        return recipeMapper.mapToResponse(recipeService.deleteRecipe(id));
    }

    @PutMapping(value = "/{id}")
    public RecipeResponse updateRecipe(@PathVariable String id, @Valid @RequestBody UpdateRecipeRequest recipeRequest) {
        UpdateRecipeDTO recipeDto = updateRecipeMapper.mapFromRequest(recipeRequest);
        recipeDto.setRecipeId(id);
        return updateRecipeMapper.mapToResponse(recipeService.updateRecipe(recipeDto));
    }

    @GetMapping(value = "/userId/{userId}")
    public List<RecipeResponse> getAllUserRecipes(@PathVariable String userId) {
        return recipeService.getAllUserRecipes(userId)
                .stream()
                .map(recipeMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/filter/{title}")
    public List<RecipeResponse> simpleFilterRecipes(@PathVariable String title) {
        return recipeService.simpleRecipeFiltering(title)
                .stream()
                .map(recipeMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/complex_filter")
    public List<RecipeResponse> complexFilterRecipes(@Valid @RequestBody RecipeFilterCriteria recipeFilterCriteria) {
        return recipeService.complexRecipeFilter(recipeFilterCriteria)
                .stream()
                .map(recipeMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @PutMapping(value = "/viewed/{id}")
    public RecipeResponse updateNoViewsRecipe(@PathVariable String id) {
        return recipeMapper.mapToResponse(recipeService.updateNoViews(id));
    }
}
