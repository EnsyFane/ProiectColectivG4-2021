package com.kitchen.iChef.Service;

import com.kitchen.iChef.DTO.RecipeDTO;
import com.kitchen.iChef.DTO.RecipeIngredientDTO;
import com.kitchen.iChef.Domain.Ingredient;
import com.kitchen.iChef.Domain.Recipe;
import com.kitchen.iChef.Domain.RecipeIngredient;
import com.kitchen.iChef.Exceptions.ResourceNotFoundException;
import com.kitchen.iChef.Mapper.RecipeIngredientMapper;
import com.kitchen.iChef.Mapper.RecipeMapper;
import com.kitchen.iChef.Repository.IngredientRepository;
import com.kitchen.iChef.Repository.RecipeIngredientRepository;
import com.kitchen.iChef.Repository.RecipeRepository;
import com.kitchen.iChef.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final UserRepository userRepository;

    private final RecipeIngredientMapper recipeIngredientMapper = new RecipeIngredientMapper();
    private final RecipeMapper recipeMapper = new RecipeMapper(recipeIngredientMapper);


    @Autowired
    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, RecipeIngredientRepository recipeIngredientRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.userRepository = userRepository;

    }

    public RecipeDTO addRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = recipeMapper.mapToEntity(recipeDTO);
        recipe.setAppUser(userRepository.findOne(recipeDTO.getUserId()));
        try {
            recipeRepository.save(recipe);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("No recipe with this id");
        }
        List<RecipeIngredientDTO> recipeIngredientDTOS = recipeDTO.getRecipeIngredientDTOSList();
        for (RecipeIngredientDTO r : recipeIngredientDTOS) {
            Optional<Ingredient> ingredient = ingredientRepository.findByName(r.getIngredientName());
            if (!ingredient.isPresent()) {
                Ingredient i = new Ingredient();
                i.setName(r.getIngredientName());
                ingredientRepository.save(i);
                RecipeIngredient ri = new RecipeIngredient();
                ri.setAmount(r.getAmount());
                ri.setIngredient(i);
                ri.setRecipe(recipe);
                recipeIngredientRepository.save(ri);

            } else {
                RecipeIngredient ri = new RecipeIngredient();
                ri.setAmount(r.getAmount());
                ri.setIngredient(ingredient.get());
                ri.setRecipe(recipe);
                recipeIngredientRepository.save(ri);

            }
        }
        return recipeDTO;
    }

    public RecipeDTO getRecipe(String id) {
        Recipe r;
        try {
            r = recipeRepository.findOne(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("No recipe with this id");
        }

        RecipeDTO recipeDTO = recipeMapper.mapToDTO(r);

        List<RecipeIngredientDTO> recipeIngredientDTOList = getRecipeIngredientsList(r);

        recipeDTO.setRecipeIngredientDTOSList(recipeIngredientDTOList);
        return recipeDTO;
    }

    public List<RecipeDTO> getAllRecipes() {
        List<RecipeDTO> list = new ArrayList<>();
        for (Recipe r : recipeRepository.findAll()) {
            RecipeDTO recipeDTO = recipeMapper.mapToDTO(r);

            List<RecipeIngredientDTO> recipeIngredientDTOList = getRecipeIngredientsList(r);

            recipeDTO.setRecipeIngredientDTOSList(recipeIngredientDTOList);
            list.add(recipeDTO);
        }
        return list;
    }

    public RecipeDTO deleteRecipe(String id) {
        Recipe r;
        try {
            r = recipeRepository.findOne(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("No recipe with this id");
        }
        RecipeDTO recipeDTO = recipeMapper.mapToDTO(r);
        recipeDTO.setRecipeIngredientDTOSList(getRecipeIngredientsList(r));

        try {
            recipeRepository.delete(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("No recipe with this id");
        }

        for (RecipeIngredient ri : recipeIngredientRepository.findAll()) {
            if (ri.getRecipe().getRecipeId().equals(r.getRecipeId())) {
                try {
                    recipeIngredientRepository.delete(ri.getRecipeIngredientId());
                } catch (Exception ex) {
                    throw new ResourceNotFoundException("No recipeIngredient with this id");
                }
            }
        }
        return recipeDTO;
    }

    public RecipeDTO updateRecipe(RecipeDTO recipeDTO) {
        throw new NotImplementedException();
    }

    public List<RecipeDTO> getAllUserRecipes(String userId) {
        List<RecipeDTO> list = new ArrayList<>();
        for (Recipe r : recipeRepository.findAll()) {
            if (r.getAppUser().getUserId().equals(userId)) {
                RecipeDTO recipeDTO = recipeMapper.mapToDTO(r);

                List<RecipeIngredientDTO> recipeIngredientDTOList = getRecipeIngredientsList(r);

                recipeDTO.setRecipeIngredientDTOSList(recipeIngredientDTOList);
                list.add(recipeDTO);
            }
        }
        return list;
    }

    private List<RecipeIngredientDTO> getRecipeIngredientsList(Recipe r) {
        List<RecipeIngredientDTO> recipeIngredientDTOList = new ArrayList<>();
        for (RecipeIngredient ri : recipeIngredientRepository.findAll()) {
            if (ri.getRecipe().getRecipeId().equals(r.getRecipeId())) {
                for (Ingredient i : ingredientRepository.findAll()) {
                    if (ri.getIngredient().getIngredientId().equals(i.getIngredientId())) {
                        RecipeIngredientDTO riDTO = new RecipeIngredientDTO();
                        riDTO.setIngredientName(i.getName());
                        riDTO.setAmount(ri.getAmount());
                        recipeIngredientDTOList.add(riDTO);
                    }
                }
            }
        }
        return recipeIngredientDTOList;
    }
}
