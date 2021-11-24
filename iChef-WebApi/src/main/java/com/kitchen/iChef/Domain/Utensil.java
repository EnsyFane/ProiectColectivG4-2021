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
@Table(name = "utensil")
public class Utensil {
    @Id
    @Column(name = "utensil_id")
    private String utensilId;

    private String name;

    @PrePersist
    private void ensureId() {
        this.setUtensilId(UUID.randomUUID().toString());
    }
}
