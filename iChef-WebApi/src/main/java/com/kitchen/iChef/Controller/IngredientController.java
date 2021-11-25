package com.kitchen.iChef.Controller;

import com.kitchen.iChef.Controller.Model.Request.IngredientRequest;
import com.kitchen.iChef.Controller.Model.Response.IngredientResponse;
import com.kitchen.iChef.Domain.Ingredient;
import com.kitchen.iChef.Mapper.IngredientMapper;
import com.kitchen.iChef.Service.IngredientService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;
    private final IngredientMapper ingredientMapper;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
        this.ingredientMapper = new IngredientMapper();
    }

    @GetMapping
    public List<IngredientResponse> getAllIngredients() {
        return ingredientService.getAllIngredients()
                .stream()
                .map(ingredientMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public IngredientResponse getIngredientById(@Valid @PathVariable String id) {
        return ingredientMapper.mapToResponse(ingredientService.getIngredient(id));
    }

    @PostMapping
    public IngredientResponse addIngredient(@Valid @RequestBody IngredientRequest ingredientRequest) throws Exception {
        return ingredientMapper.mapToResponse(ingredientService.addIngredient(
                ingredientMapper.mapFromRequest(ingredientRequest)));
    }

    @DeleteMapping(value = "/{id}")
    public IngredientResponse deleteIngredient(@PathVariable String id) {
        return ingredientMapper.mapToResponse(ingredientService.deleteIngredient(id));
    }

    @PutMapping(value = "/{id}")
    public IngredientResponse updateIngredient(@PathVariable String id, @Valid @RequestBody IngredientRequest ingredientRequest) {
        Ingredient ingredient = ingredientMapper.mapFromRequest(ingredientRequest);
        ingredient.setIngredientId(id);
        return ingredientMapper.mapToResponse(ingredientService.updateIngredient(ingredient));
    }
}
