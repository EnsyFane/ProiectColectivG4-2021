package com.kitchen.iChef.Controller;

import com.kitchen.iChef.Controller.Model.Request.FilterRequest;
import com.kitchen.iChef.Controller.Model.Request.RecipeRequest;
import com.kitchen.iChef.Controller.Model.Response.RecipeResponse;
import com.kitchen.iChef.Exceptions.ResourceNotFoundException;
import com.kitchen.iChef.Mapper.RecipeIngredientMapper;
import com.kitchen.iChef.Mapper.RecipeMapper;
import com.kitchen.iChef.Mapper.RecipeUtensilMapper;
import com.kitchen.iChef.Service.RecipeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;
    private final RecipeIngredientMapper recipeIngredientMapper = new RecipeIngredientMapper();
    private final RecipeUtensilMapper recipeUtensilMapper = new RecipeUtensilMapper();

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
        this.recipeMapper = new RecipeMapper(recipeIngredientMapper, recipeUtensilMapper);
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

    @DeleteMapping(value = "/{id}")
    public RecipeResponse deleteRecipe(@PathVariable String id) {
        return recipeMapper.mapToResponse(recipeService.deleteRecipe(id));
    }

    @PutMapping(value = "/{id}")
    public RecipeResponse updateRecipe(@PathVariable String id, @Valid @RequestBody RecipeRequest recipeRequest) {
        throw new ResourceNotFoundException("Not yet implemented.");
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
    public List<RecipeResponse> complexFilterRecipes(@Valid @RequestBody FilterRequest filterRequest) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return recipeService.complexRecipeFiltering(filterRequest)
                .stream()
                .map(recipeMapper::mapToResponse)
                .collect(Collectors.toList());
    }

}
