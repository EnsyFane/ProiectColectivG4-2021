package com.kitchen.iChef.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "recipe_ingredient")
public class RecipeIngredient {
    @Id
    private String recipeIngredientId;

    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    @ManyToOne
    private Recipe recipe;

    @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id")
    @ManyToOne
    private Ingredient ingredient;

    private int amount;

    private String measurementUnit;

    @PrePersist
    private void ensureId() {
        this.setRecipeIngredientId(UUID.randomUUID().toString());
    }
}
