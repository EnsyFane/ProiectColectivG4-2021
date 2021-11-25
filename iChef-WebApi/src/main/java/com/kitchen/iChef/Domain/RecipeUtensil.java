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
@Table(name = "recipe_utensil")
public class RecipeUtensil {

    @Id
    private String recipeUtensilId;

    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Recipe recipe;

    @JoinColumn(name = "utensil_id", referencedColumnName = "utensil_id")
    @ManyToOne
    private Utensil utensil;

    @PrePersist
    private void ensureId() {
        this.setRecipeUtensilId(UUID.randomUUID().toString());
    }
}

