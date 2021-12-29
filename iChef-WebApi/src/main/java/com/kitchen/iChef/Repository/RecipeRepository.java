package com.kitchen.iChef.Repository;

import com.kitchen.iChef.Controller.Model.Request.FilterRequest;
import com.kitchen.iChef.Domain.*;
import com.kitchen.iChef.Repository.Interfaces.ICrudRepository;
import com.kitchen.iChef.Repository.Interfaces.IRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RecipeRepository implements ICrudRepository<Recipe, String> {
    private final IRecipeRepository iRecipeRepository;
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    @Autowired
    public RecipeRepository(IRecipeRepository iRecipeRepository, EntityManager entityManager) {
        this.iRecipeRepository = iRecipeRepository;
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    @Override
    public Recipe findOne(String s) {
        if (iRecipeRepository.findById(s).isPresent()) {
            return iRecipeRepository.findById(s).get();
        }
        return null;
    }

    public List<Recipe> findRecipesByTitle(String title) {
        List<Recipe> filteredRecipes = new ArrayList<>();
        Iterable<Recipe> recipes = iRecipeRepository.findRecipesByTitleContains(title);
        for (Recipe recipe : recipes) {
            filteredRecipes.add(recipe);
        }
        return filteredRecipes;
    }

    @Override
    public List<Recipe> findAll() {
        List<Recipe> allRecipes = new ArrayList<>();
        Iterable<Recipe> recipes = iRecipeRepository.findAll();
        for (Recipe recipe : recipes) {
            allRecipes.add(recipe);
        }
        return allRecipes;
    }

    public List<Recipe> findAll(Sort sort) {
        List<Recipe> allRecipes = new ArrayList<>();
        Iterable<Recipe> recipes = iRecipeRepository.findAll(sort);
        for (Recipe recipe : recipes) {
            allRecipes.add(recipe);
        }
        return allRecipes;
    }

    public List<Recipe> findByIngredients(RecipeFilterCriteria recipeFilterCriteria) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        CriteriaQuery<Recipe> cq = criteriaBuilder.createQuery(Recipe.class);
        Root<Recipe> fromRecipe = cq.from(Recipe.class);

        Predicate predicate = getPredicate(recipeFilterCriteria, fromRecipe, cq);

        TypedQuery<Recipe> typedQuery = entityManager.createQuery(cq

                .select(fromRecipe)
                .where(predicate)
                .distinct(true)
        );

        return typedQuery.getResultList();
    }

    private Predicate getPredicate(RecipeFilterCriteria recipeFilterCriteria, Root<Recipe> fromRecipe, CriteriaQuery<Recipe> cq) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Root<RecipeIngredient> fromRecipeIngredient = cq.from(RecipeIngredient.class);
        Root<Ingredient> fromIngredient = cq.from(Ingredient.class);
        Root<RecipeUtensil> fromRecipeUtensil = cq.from(RecipeUtensil.class);
        Root<Utensil> fromUtensil = cq.from(Utensil.class);

        Join<RecipeIngredient, Recipe> recipeJoin = fromRecipeIngredient.join(RecipeIngredient_.recipe);
        Join<RecipeIngredient, Ingredient> ingredientJoin = fromRecipeIngredient.join(RecipeIngredient_.ingredient);
        Join<RecipeUtensil, Recipe> recipeJoinU = fromRecipeUtensil.join(RecipeUtensil_.recipe);
        Join<RecipeUtensil, Utensil> utensilJoin = fromRecipeUtensil.join(RecipeUtensil_.utensil);


        List<Predicate> predicates = new ArrayList<>();
        for (FilterRequest filterRequest : recipeFilterCriteria.getFilters()) {

            String fieldName = filterRequest.getField();

            switch (fieldName) {

                case "title":
                    predicates.add(criteriaBuilder.like(fromRecipe.get("title"), "%" + filterRequest.getText() + "%")
                    );
                    break;

                case "ingredients":
                    List<String> ingredientList = Arrays.asList(filterRequest.getText().split(" ").clone());
                    predicates.add(criteriaBuilder.equal(fromRecipe.get(Recipe_.RECIPE_ID), recipeJoin));
                    predicates.add(criteriaBuilder.equal(fromIngredient.get(Ingredient_.INGREDIENT_ID), ingredientJoin));
                    predicates.add(fromIngredient.get(Ingredient_.NAME).in(ingredientList));
                    break;

                case "utensils":
                    List<String> utensilList = Arrays.asList(filterRequest.getText().split(" ").clone());
                    predicates.add(criteriaBuilder.equal(fromRecipe.get(Recipe_.RECIPE_ID), recipeJoinU));
                    predicates.add(criteriaBuilder.equal(fromUtensil.get(Utensil_.UTENSIL_ID), utensilJoin));
                    predicates.add(fromUtensil.get(Utensil_.NAME).in(utensilList));
                    break;

                default:
                    if (filterRequest.getOperation().toString().equals("equal")) {

                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(fromRecipe.get(fieldName), filterRequest.getText()));
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(fromRecipe.get(fieldName), filterRequest.getText()));
                    } else
                        predicates.add((Predicate) criteriaBuilder.getClass().getMethod(filterRequest.getOperation().toString(), Expression.class, Comparable.class)
                                .invoke(criteriaBuilder, fromRecipe.get(fieldName), filterRequest.getText()));

            }


        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


    @Override
    public Recipe save(Recipe entity) {
        return iRecipeRepository.save(entity);
    }

    @Override
    public Recipe delete(String s) {
        Recipe recipe = findOne(s);
        iRecipeRepository.deleteById(s);
        return recipe;
    }

    @Override
    public Recipe update(Recipe entity) {
        Recipe recipeInDb = findOne(entity.getRecipeId());
        recipeInDb.setTitle(entity.getTitle());
        recipeInDb.setImagePath(entity.getImagePath());
        recipeInDb.setDifficulty(entity.getDifficulty());
        recipeInDb.setNotes(entity.getNotes());
        recipeInDb.setPortions(entity.getPortions());
        recipeInDb.setPreparationTime(entity.getPreparationTime());
        recipeInDb.setSteps(entity.getSteps());
        iRecipeRepository.save(recipeInDb);
        return recipeInDb;
    }

    public Recipe updateNoViews(String id) {
        Recipe recipeInDb = findOne(id);
        recipeInDb.setNumberOfViews(recipeInDb.getNumberOfViews() + 1);
        iRecipeRepository.save(recipeInDb);
        return recipeInDb;
    }
}
