package com.kitchen.iChef.Service;

import com.kitchen.iChef.Controller.Model.Request.FilterRequest;
import com.kitchen.iChef.DTO.RecipeDTO;
import com.kitchen.iChef.DTO.RecipeIngredientDTO;
import com.kitchen.iChef.DTO.RecipeUtensilDTO;
import com.kitchen.iChef.Domain.*;
import com.kitchen.iChef.Exceptions.ResourceNotFoundException;
import com.kitchen.iChef.Mapper.RecipeIngredientMapper;
import com.kitchen.iChef.Mapper.RecipeMapper;
import com.kitchen.iChef.Mapper.RecipeUtensilMapper;
import com.kitchen.iChef.Repository.*;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final UtensilRepository utensilRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeUtensilRepository recipeUtensilRepository;
    private final UserRepository userRepository;

    private final RecipeIngredientMapper recipeIngredientMapper = new RecipeIngredientMapper();
    private final RecipeUtensilMapper recipeUtensilMapper = new RecipeUtensilMapper();
    private final RecipeMapper recipeMapper = new RecipeMapper(recipeIngredientMapper, recipeUtensilMapper);


    @Autowired
    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, UtensilRepository utensilRepository, RecipeIngredientRepository recipeIngredientRepository, RecipeUtensilRepository recipeUtensilRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.utensilRepository = utensilRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeUtensilRepository = recipeUtensilRepository;
        this.userRepository = userRepository;

    }

    public RecipeDTO addRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = recipeMapper.mapToEntity(recipeDTO);
        AppUser user;
        try {
            user = userRepository.findOne(recipeDTO.getUserId());
        } catch (Exception ex) {
            throw new ResourceNotFoundException("No user with this id");
        }
        recipe.setAppUser(user);
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
                ri.setMeasurementUnit(r.getMeasurementUnit());
                ri.setIngredient(i);
                ri.setRecipe(recipe);
                recipeIngredientRepository.save(ri);
            } else {
                RecipeIngredient ri = new RecipeIngredient();
                ri.setAmount(r.getAmount());
                ri.setMeasurementUnit(r.getMeasurementUnit());
                ri.setIngredient(ingredient.get());
                ri.setRecipe(recipe);
                recipeIngredientRepository.save(ri);
            }
        }

        List<RecipeUtensilDTO> recipeUtensilDTOS = recipeDTO.getRecipeUtensilDTOSList();
        for (RecipeUtensilDTO r : recipeUtensilDTOS) {
            Optional<Utensil> utensil = utensilRepository.findByName(r.getUtensilName());
            if (!utensil.isPresent()) {
                Utensil u = new Utensil();
                u.setName(r.getUtensilName());
                utensilRepository.save(u);
                RecipeUtensil ru = new RecipeUtensil();
                ru.setRecipe(recipe);
                ru.setUtensil(u);
                recipeUtensilRepository.save(ru);
            } else {
                RecipeUtensil ru = new RecipeUtensil();
                ru.setRecipe(recipe);
                ru.setUtensil(utensil.get());
                recipeUtensilRepository.save(ru);
            }
        }
        recipeDTO.setRecipeId(recipe.getRecipeId());
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

        List<RecipeUtensilDTO> recipeUtensilDTOList = getRecipeUtensilsList(r);
        recipeDTO.setRecipeUtensilDTOSList(recipeUtensilDTOList);

        return recipeDTO;
    }

    public List<RecipeDTO> getAllRecipes() {
        List<RecipeDTO> list = new ArrayList<>();
        for (Recipe r : recipeRepository.findAll()) {
            RecipeDTO recipeDTO = recipeMapper.mapToDTO(r);

            List<RecipeIngredientDTO> recipeIngredientDTOList = getRecipeIngredientsList(r);
            recipeDTO.setRecipeIngredientDTOSList(recipeIngredientDTOList);

            List<RecipeUtensilDTO> recipeUtensilDTOList = getRecipeUtensilsList(r);
            recipeDTO.setRecipeUtensilDTOSList(recipeUtensilDTOList);

            list.add(recipeDTO);
        }
        return list;
    }
    public List<RecipeDTO> simpleRecipeFiltering(String text)
    {
        List<RecipeDTO> recipeDTOS = new ArrayList<>();
        for (Recipe r : recipeRepository.findRecipesByTitle(text)) {
            RecipeDTO recipeDTO = recipeMapper.mapToDTO(r);

            List<RecipeIngredientDTO> recipeIngredientDTOList = getRecipeIngredientsList(r);
            recipeDTO.setRecipeIngredientDTOSList(recipeIngredientDTOList);

            List<RecipeUtensilDTO> recipeUtensilDTOList = getRecipeUtensilsList(r);
            recipeDTO.setRecipeUtensilDTOSList(recipeUtensilDTOList);

            recipeDTOS.add(recipeDTO);
        }
        return recipeDTOS;

    }

    public List<RecipeDTO> complexRecipeFilter(RecipeFilterCriteria recipeFilterCriteria) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<RecipeDTO> recipeDTOS = new ArrayList<>();
        List <Recipe> recipes=recipeRepository.findBySteps(recipeFilterCriteria);
        for (Recipe r : recipes) {
            RecipeDTO recipeDTO = recipeMapper.mapToDTO(r);

            List<RecipeIngredientDTO> recipeIngredientDTOList = getRecipeIngredientsList(r);
            recipeDTO.setRecipeIngredientDTOSList(recipeIngredientDTOList);

            List<RecipeUtensilDTO> recipeUtensilDTOList = getRecipeUtensilsList(r);
            recipeDTO.setRecipeUtensilDTOSList(recipeUtensilDTOList);

            recipeDTOS.add(recipeDTO);
        }
        return recipeDTOS;

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
        recipeDTO.setRecipeUtensilDTOSList(getRecipeUtensilsList(r));

        try {
            recipeRepository.delete(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("No recipe with this id");
        }

        for (RecipeUtensil ru : recipeUtensilRepository.findAll()) {
            if (ru.getRecipe().getRecipeId().equals(r.getRecipeId())) {
                try {
                    recipeUtensilRepository.delete(ru.getRecipeUtensilId());
                } catch (Exception ex) {
                    throw new ResourceNotFoundException("No recipeUtensil with this id");
                }
            }
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
        throw new ResourceNotFoundException("Not yet implemented.");
    }

    public List<RecipeDTO> getAllUserRecipes(String userId) {
        List<RecipeDTO> list = new ArrayList<>();
        for (Recipe r : recipeRepository.findAll()) {
            if (r.getAppUser().getUserId().equals(userId)) {
                RecipeDTO recipeDTO = recipeMapper.mapToDTO(r);

                List<RecipeIngredientDTO> recipeIngredientDTOList = getRecipeIngredientsList(r);
                recipeDTO.setRecipeIngredientDTOSList(recipeIngredientDTOList);

                List<RecipeUtensilDTO> recipeUtensilDTOList = getRecipeUtensilsList(r);
                recipeDTO.setRecipeUtensilDTOSList(recipeUtensilDTOList);

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
                        riDTO.setMeasurementUnit(ri.getMeasurementUnit());
                        recipeIngredientDTOList.add(riDTO);
                    }
                }
            }
        }
        return recipeIngredientDTOList;
    }

    private List<RecipeUtensilDTO> getRecipeUtensilsList(Recipe r) {
        List<RecipeUtensilDTO> recipeUtensilDTOList = new ArrayList<>();
        for (RecipeUtensil ru : recipeUtensilRepository.findAll()) {
            if (ru.getRecipe().getRecipeId().equals(r.getRecipeId())) {
                for (Utensil u : utensilRepository.findAll()) {
                    if (ru.getUtensil().getUtensilId().equals(u.getUtensilId())) {
                        RecipeUtensilDTO ruDTO = new RecipeUtensilDTO();
                        ruDTO.setUtensilName(u.getName());
                        recipeUtensilDTOList.add(ruDTO);
                    }
                }
            }
        }
        return recipeUtensilDTOList;
    }
}
