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
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "review_id")
    private String reviewId;

    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    @ManyToOne
    private Recipe recipe;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private AppUser user;

    private int rating = -1;
    @Column(columnDefinition = "TEXT")
    private String comment;

    @PrePersist
    private void ensureId() {
        this.setReviewId(UUID.randomUUID().toString());
    }
}
