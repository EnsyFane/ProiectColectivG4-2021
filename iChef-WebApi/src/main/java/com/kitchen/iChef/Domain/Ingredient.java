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
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @Column(name = "ingredient_id")
    private String ingredientId;

    private String name;

    @PrePersist
    private void ensureId() {
        this.setIngredientId(UUID.randomUUID().toString());
    }
}
