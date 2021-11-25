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
@Table(name = "recipe")
public class Recipe {
    @Id
    @Column(name = "recipe_id")
    private String recipeId;
    private String title;
    private String steps;
    private Float rating;
    private Float difficulty;
    private Integer preparationTime;
    private Integer portions;
    private String notes;
    private Integer numberOfViews;
    private String imagePath;

    @JoinColumn(name = "app_user_id", referencedColumnName = "user_id")
    @ManyToOne
    private AppUser appUser;

    @PrePersist
    private void ensureId() {
        this.setRecipeId(UUID.randomUUID().toString());
    }
}
