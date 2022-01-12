package com.kitchen.iChef.Repository.Interfaces;

import com.kitchen.iChef.Domain.AppUser;
import com.kitchen.iChef.Domain.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IReviewRepository  extends CrudRepository<Review, String> {
    @Query(value = "SELECT * FROM review WHERE recipe_id = :recipe_id", nativeQuery = true)
    List<Review> findByRecipeId(@Param("recipe_id") String recipe_id);

    @Query(value = "SELECT * FROM review WHERE recipe_id = :recipe_id and user_id = :user_id", nativeQuery = true)
    Optional<Review> findByRecipeIdAndUserId(@Param("recipe_id") String recipe_id, @Param("user_id") String user_id);
}
