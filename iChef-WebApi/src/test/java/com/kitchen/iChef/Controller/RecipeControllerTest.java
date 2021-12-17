package com.kitchen.iChef.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitchen.iChef.Controller.Model.Request.*;
import com.kitchen.iChef.DTO.RecipeDTO;
import com.kitchen.iChef.DTO.RecipeIngredientDTO;
import com.kitchen.iChef.DTO.RecipeUtensilDTO;
import com.kitchen.iChef.DTO.UpdateRecipeDTO;
import com.kitchen.iChef.Mapper.*;
import com.kitchen.iChef.Repository.RecipeFilterCriteria;
import com.kitchen.iChef.Service.RecipeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {
    private final String url = "/recipes";

    @Mock
    RecipeService recipeService;

    RecipeController recipeController;
    RecipeMapper recipeMapper;
    UpdateRecipeMapper updateRecipeMapper;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        RecipeIngredientMapper recipeIngredientMapper = new RecipeIngredientMapper();
        RecipeUtensilMapper recipeUtensilMapper = new RecipeUtensilMapper();
        recipeMapper = new RecipeMapper(recipeIngredientMapper,recipeUtensilMapper);
        updateRecipeMapper = new UpdateRecipeMapper(recipeIngredientMapper,recipeUtensilMapper);
        recipeController = new RecipeController(recipeService);
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

    private UpdateRecipeRequest createUpdateRecipeRequest(String title, String steps, Float difficulty,Integer preparationTime, Integer portions, String notes,
                                                          String imagePath, List<RecipeIngredientRequest> recipeIngredientRequests,
                                                          List<RecipeUtensilRequest> recipeUtensilRequests)
    {
        UpdateRecipeRequest updateRecipeRequest = new UpdateRecipeRequest();
        updateRecipeRequest.setTitle(title);
        updateRecipeRequest.setSteps(steps);
        updateRecipeRequest.setDifficulty(difficulty);
        updateRecipeRequest.setPreparationTime(preparationTime);
        updateRecipeRequest.setPortions(portions);
        updateRecipeRequest.setNotes(notes);
        updateRecipeRequest.setImagePath(imagePath);
        updateRecipeRequest.setRecipeIngredientList(recipeIngredientRequests);
        updateRecipeRequest.setRecipeUtensilList(recipeUtensilRequests);
        return updateRecipeRequest;
    }

    private List<RecipeDTO> createRecipeDtoList()
    {

        List<RecipeDTO> recipes = new ArrayList<>();
        List<RecipeIngredientDTO> list1 = new ArrayList<>();
        List<RecipeUtensilDTO> list2 = new ArrayList<>();
        RecipeDTO recipeDTO1 = createRecipeDTO("1","Pizza","steps",4.5f,
                3.4f,30,4,"notes",10,"imagePath",
                "1",list1,list2);
        RecipeDTO recipeDTO2 = createRecipeDTO("2","Burger","steps",4.5f,
                3.4f,30,4,"notes",10,"imagePath",
                "1",list1,list2);
        RecipeDTO recipeDTO3 = createRecipeDTO("1","Pasta","steps",4.5f,
                3.4f,30,4,"notes",10,"imagePath",
                "1",list1,list2);
        recipes.add(recipeDTO1);
        recipes.add(recipeDTO2);
        recipes.add(recipeDTO3);
        return recipes;
    }

    @Test
    void getAllRecipes() throws Exception {
        List<RecipeDTO> recipes = createRecipeDtoList();
        when(recipeService.getAllRecipes()).thenReturn(recipes);
        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));
    }

    @Test
    void addRecipe() throws Exception {

        List<RecipeIngredientRequest> list1 = new ArrayList<>();
        List<RecipeUtensilRequest> list2 = new ArrayList<>();

        RecipeIngredientRequest recipeIngredientRequest = new RecipeIngredientRequest();
        recipeIngredientRequest.setAmount(10);
        recipeIngredientRequest.setMeasurementUnit("spoon");
        recipeIngredientRequest.setIngredientName("salt");

        RecipeUtensilRequest recipeUtensilRequest = new RecipeUtensilRequest();
        recipeUtensilRequest.setUtensilName("mixer");

        list1.add(recipeIngredientRequest);
        list2.add(recipeUtensilRequest);

        List<RecipeIngredientDTO> list11 = new ArrayList<>();
        List<RecipeUtensilDTO> list12 = new ArrayList<>();

        RecipeIngredientDTO recipeIngredientDTO = new RecipeIngredientDTO();
        recipeIngredientDTO.setAmount(10);
        recipeIngredientDTO.setMeasurementUnit("spoon");
        recipeIngredientDTO.setIngredientName("salt");
        list11.add(recipeIngredientDTO);

        RecipeUtensilDTO recipeUtensilDTO = new RecipeUtensilDTO();
        recipeUtensilDTO.setUtensilName("mixer");
        list12.add(recipeUtensilDTO);

        RecipeDTO recipeDTO1 = createRecipeDTO("1","Pizza","steps1",5.5f,
                4.3f,20,2,"notes1",1000,"imagePath1", "1",list11,list12);

        RecipeRequest recipeRequest = createRecipeRequest("Pizza","steps",4.5f,30,
                4,"notes","imagePath","1",list1,list2);

        when(recipeService.addRecipe(any(RecipeDTO.class))).thenReturn(recipeDTO1);

        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recipeRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Pizza")));
    }

    @Test
    void getRecipeById() throws Exception {
        List<RecipeIngredientDTO> list1 = new ArrayList<>();
        List<RecipeUtensilDTO> list2 = new ArrayList<>();
        RecipeDTO recipeDTO1 = createRecipeDTO("1","Pizza","steps",4.5f,
                3.4f,30,4,"notes",10,"imagePath",
                "1",list1,list2);

        when(recipeService.getRecipe(anyString())).thenReturn(recipeDTO1);

        mockMvc.perform(get(url + "/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title",Matchers.is("Pizza")));
    }

    @Test
    void deleteRecipe() throws Exception {

        List<RecipeIngredientDTO> list1 = new ArrayList<>();
        List<RecipeUtensilDTO> list2 = new ArrayList<>();
        RecipeDTO recipeDTO = createRecipeDTO("1","Pizza","steps",4.5f,
                3.4f,30,4,"notes",10,"imagePath",
                "1",list1,list2);

        when(recipeService.deleteRecipe(anyString())).thenReturn(recipeDTO);
        mockMvc.perform(delete("/recipes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title",Matchers.is("Pizza")));

        verify(recipeService,times(1)).deleteRecipe(anyString());
    }

    @Test
    void sortRecipes() throws Exception {

        List<RecipeDTO> recipes = createRecipeDtoList();

        SortingRequest sortingRequest = new SortingRequest();
        sortingRequest.setAscending(true);
        sortingRequest.setField("rating");

        when(recipeService.sortRecipes(anyString(),anyBoolean())).thenReturn(recipes);

        mockMvc.perform(post(url + "/sort")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(sortingRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*",Matchers.hasSize(3)));
    }

    @Test
    void updateRecipe() throws Exception {

        UpdateRecipeMapper updateRecipeMapper = new UpdateRecipeMapper(new RecipeIngredientMapper(),new RecipeUtensilMapper());

        List<RecipeIngredientRequest> list1 = new ArrayList<>();
        List<RecipeUtensilRequest> list2 = new ArrayList<>();

        RecipeIngredientRequest recipeIngredientRequest = new RecipeIngredientRequest();
        recipeIngredientRequest.setAmount(10);
        recipeIngredientRequest.setMeasurementUnit("spoon");
        recipeIngredientRequest.setIngredientName("salt");

        RecipeUtensilRequest recipeUtensilRequest = new RecipeUtensilRequest();
        recipeUtensilRequest.setUtensilName("mixer");

        list1.add(recipeIngredientRequest);
        list2.add(recipeUtensilRequest);

        UpdateRecipeRequest updateRecipeRequest = createUpdateRecipeRequest("Pizza","steps",4.5f,30,
                4,"notes","imagePath",list1,list2);

        UpdateRecipeDTO updateRecipeDTO = updateRecipeMapper.mapFromRequest(updateRecipeRequest);

        when(recipeService.updateRecipe(any(UpdateRecipeDTO.class))).thenReturn(updateRecipeDTO);

        mockMvc.perform(put(url + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updateRecipeRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Pizza")));
    }

    @Test
    void getAllUserRecipes() throws Exception {

        List<RecipeDTO> recipes = createRecipeDtoList();

        when(recipeService.getAllUserRecipes(anyString())).thenReturn(recipes);
        mockMvc.perform(get(url + "/userId/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*",Matchers.hasSize(3)));
    }

    @Test
    void simpleFilterRecipes() throws Exception {

        List<RecipeDTO> recipes = createRecipeDtoList();

        when(recipeService.simpleRecipeFiltering(anyString())).thenReturn(recipes);

        mockMvc.perform(get(url + "/filter/Pizza")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*",Matchers.hasSize(3)));
    }

    @Test
    void complexFilterRecipes() throws Exception {

        List<RecipeDTO> recipes = createRecipeDtoList();

        List<FilterRequest> filterRequests = new ArrayList<>();
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setField("difficulty");
        filterRequest.setOperation(OperationType.greaterThan);
        filterRequest.setText("FastFood");
        filterRequests.add(filterRequest);

        RecipeFilterCriteria recipeFilterCriteria = new RecipeFilterCriteria();
        recipeFilterCriteria.setFilters(filterRequests);

        when(recipeService.complexRecipeFilter(any(RecipeFilterCriteria.class))).thenReturn(recipes);

        mockMvc.perform(post(url + "/complex_filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recipeFilterCriteria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*",Matchers.hasSize(3)));
    }

    @Test
    void updateNoViewsRecipe() throws Exception {
        List<RecipeIngredientDTO> list1 = new ArrayList<>();
        List<RecipeUtensilDTO> list2 = new ArrayList<>();
        RecipeDTO recipeDTO = createRecipeDTO("1","Pizza","steps",4.5f,
                3.4f,30,4,"notes",10,"imagePath",
                "1",list1,list2);

        when(recipeService.updateNoViews(anyString())).thenReturn(recipeDTO);

        mockMvc.perform(put(url + "/viewed/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title",Matchers.is("Pizza")));
    }
}