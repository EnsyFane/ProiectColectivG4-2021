package com.kitchen.iChef.Repository;

import com.kitchen.iChef.Controller.Model.Request.FilterRequest;
import com.kitchen.iChef.Domain.Recipe;
import com.kitchen.iChef.Repository.Interfaces.ICrudRepository;
import com.kitchen.iChef.Repository.Interfaces.IRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public List<Recipe> findRecipesByTitle(String title)
    {
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
    public List<Recipe> findBySteps(RecipeFilterCriteria recipeFilterCriteria) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        CriteriaQuery<Recipe> criteriaQuery= criteriaBuilder.createQuery(Recipe.class);
        Root<Recipe> recipeRoot=criteriaQuery.from(Recipe.class);
        Predicate predicate=getPredicate(recipeFilterCriteria,recipeRoot);
        criteriaQuery.where(predicate);
        TypedQuery<Recipe> typedQuery=entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    private Predicate getPredicate(RecipeFilterCriteria recipeFilterCriteria, Root<Recipe> recipeRoot) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    List<Predicate> predicates=new ArrayList<>();
    for(FilterRequest filterRequest:recipeFilterCriteria.getFilters()) {
        if (filterRequest.getField().equals("title"))
            predicates.add(criteriaBuilder.like(recipeRoot.get("title"), "%" + filterRequest.getText() + "%")
            );
        if (filterRequest.getField().equals("difficulty"))
            predicates.add((Predicate) criteriaBuilder.getClass().getMethod(filterRequest.getOperation(), Expression.class, Comparable.class).invoke(criteriaBuilder, recipeRoot.get("difficulty"), filterRequest.getText())
            );
        if (filterRequest.getField().equals("portions"))
            predicates.add((Predicate) criteriaBuilder.getClass().getMethod(filterRequest.getOperation(), Expression.class, Comparable.class).invoke(criteriaBuilder, recipeRoot.get("portions"), filterRequest.getText())
            );
        if (filterRequest.getField().equals("preparationTime"))
            predicates.add((Predicate) criteriaBuilder.getClass().getMethod(filterRequest.getOperation(), Expression.class, Object.class).invoke(criteriaBuilder, recipeRoot.get("preparationTime"), filterRequest.getText())
            );
        if (filterRequest.getField().equals("rating"))
            predicates.add((Predicate) criteriaBuilder.getClass().getMethod(filterRequest.getOperation(), Expression.class, Comparable.class).invoke(criteriaBuilder, recipeRoot.get("rating"), filterRequest.getText())
            );
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
        iRecipeRepository.deleteById(entity.getRecipeId());
        iRecipeRepository.save(entity);
        return entity;
    }
}
