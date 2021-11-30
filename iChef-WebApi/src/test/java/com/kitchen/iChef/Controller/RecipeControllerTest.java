package com.kitchen.iChef.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitchen.iChef.Controller.Model.Request.RecipeIngredientRequest;
import com.kitchen.iChef.Controller.Model.Request.RecipeRequest;
import com.kitchen.iChef.Controller.Model.Request.RecipeUtensilRequest;
import com.kitchen.iChef.Controller.Model.Response.RecipeResponse;
import com.kitchen.iChef.DTO.RecipeDTO;
import com.kitchen.iChef.DTO.RecipeIngredientDTO;
import com.kitchen.iChef.DTO.RecipeUtensilDTO;
import com.kitchen.iChef.Domain.Ingredient;
import com.kitchen.iChef.Domain.RecipeIngredient;
import com.kitchen.iChef.Mapper.IngredientMapper;
import com.kitchen.iChef.Mapper.RecipeIngredientMapper;
import com.kitchen.iChef.Mapper.RecipeMapper;
import com.kitchen.iChef.Mapper.RecipeUtensilMapper;
import com.kitchen.iChef.Service.RecipeService;
import io.swagger.models.auth.In;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class RecipeControllerTest {
    private String url = "/recipes";

    @Mock
    RecipeService recipeService;

    @InjectMocks
    RecipeController recipeController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private RecipeDTO createRecipeDTO(String recipeId, String title, String steps, Float rating, Float difficulty,
                                      Integer preparationTime, Integer portions, String notes,
                                      Integer numberOfViews, String imagePath, String userId,
                                      List<RecipeIngredientDTO> recipeIngredientDTOSList,
                                      List<RecipeUtensilDTO> recipeUtensilDTOSList)
    {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setRecipeId(recipeId);
        recipeDTO.setTitle(title);
        recipeDTO.setSteps(steps);
        recipeDTO.setRating(rating);
        recipeDTO.setDifficulty(difficulty);
        recipeDTO.setPreparationTime(preparationTime);
        recipeDTO.setPortions(portions);
        recipeDTO.setNotes(notes);
        recipeDTO.setNumberOfViews(numberOfViews);
        recipeDTO.setImagePath(imagePath);
        recipeDTO.setUserId(userId);
        recipeDTO.setRecipeIngredientDTOSList(recipeIngredientDTOSList);
        recipeDTO.setRecipeUtensilDTOSList(recipeUtensilDTOSList);
        return recipeDTO;
    }

    private RecipeRequest createRecipeRequest(String title, String steps, Float difficulty,Integer preparationTime, Integer portions, String notes,
                                              String imagePath, String userId, List<RecipeIngredientRequest> recipeIngredientRequests,
                                              List<RecipeUtensilRequest> recipeUtensilRequests)
    {
        RecipeRequest recipeRequest = new RecipeRequest();
        recipeRequest.setTitle(title);
        recipeRequest.setSteps(steps);
        recipeRequest.setDifficulty(difficulty);
        recipeRequest.setPreparationTime(preparationTime);
        recipeRequest.setPortions(portions);
        recipeRequest.setNotes(notes);
        recipeRequest.setImagePath(imagePath);
        recipeRequest.setUserId(userId);
        recipeRequest.setRecipeIngredientList(recipeIngredientRequests);
        recipeRequest.setRecipeUtensilList(recipeUtensilRequests);
        return recipeRequest;
    }

    @Test
    void getAllRecipes() throws Exception {
        List<RecipeIngredientDTO> list1 = new ArrayList<>();
        List<RecipeUtensilDTO> list2 = new ArrayList<>();
        RecipeDTO recipeDTO1 = createRecipeDTO("1","Pizza","das",4.5f,
                3.4f,30,4,"fsdfs",10,"fsdfsd",
                "1",list1,list2);
        RecipeDTO recipeDTO2 = createRecipeDTO("2","Burger","das",4.5f,
                3.4f,30,4,"fsdfs",10,"fsdfsd",
                "1",list1,list2);
        List<RecipeDTO> recipes = new ArrayList();
        recipes.add(recipeDTO1);
        recipes.add(recipeDTO2);
        when(recipeService.getAllRecipes()).thenReturn(recipes);
        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    void addRecipe() throws Exception {
        List<RecipeIngredientRequest> list1 = new ArrayList<>();
        RecipeIngredientRequest recipeIngredientRequest = new RecipeIngredientRequest();
        recipeIngredientRequest.setAmount(3);
        recipeIngredientRequest.setIngredientName("fdsf");
        recipeIngredientRequest.setMeasurementUnit("fsdfsd");
        list1.add(recipeIngredientRequest);
        List<RecipeUtensilRequest> list2 = new ArrayList<>();
        RecipeUtensilRequest recipeUtensilRequest = new RecipeUtensilRequest();
        recipeUtensilRequest.setUtensilName("sfsdf");
        list2.add(recipeUtensilRequest);

        List<RecipeIngredientDTO> list11 = new ArrayList<>();
        List<RecipeUtensilDTO> list12 = new ArrayList<>();
        RecipeDTO recipeDTO1 = createRecipeDTO("1","Pizza","das",4.5f,
                3.4f,30,4,"fsdfs",10,"fsdfsd",
                "1",list11,list12);

        RecipeRequest recipeRequest = createRecipeRequest("Pizza","adsad",4.5f,30,4,"daads","fsdafds","1",list1,list2);

        lenient().when(recipeService.addRecipe(any(RecipeDTO.class))).thenReturn(any(RecipeDTO.class));

        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recipeRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(1)));
    }

    @Test
    void getRecipeById() throws Exception {
        List<RecipeIngredientDTO> list1 = new ArrayList<>();
        List<RecipeUtensilDTO> list2 = new ArrayList<>();
        RecipeDTO recipeDTO1 = createRecipeDTO("1","Pizza","das",4.5f,
                3.4f,30,4,"fsdfs",10,"fsdfsd",
                "1",list1,list2);

        when(recipeService.getRecipe(anyString())).thenReturn(recipeDTO1);

        mockMvc.perform(get("/recipes/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title",Matchers.is("Pizza")));
    }

    @Test
    void deleteRecipe() throws Exception {
        mockMvc.perform(delete("/recipes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(recipeService).deleteRecipe(anyString());
    }
}