package com.kitchen.iChef.Repository.Interfaces;

import com.kitchen.iChef.Domain.Token;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ITokenRepository extends CrudRepository<Token, String> {

    @Query(value = "SELECT * FROM Token WHERE value = :value", nativeQuery = true)
    Optional<Token> findByValue(String value);

    @Query(value = "SELECT * FROM Token WHERE user_id = :userId", nativeQuery = true)
    Optional<Token> findByUserId(String userId);

    @Query(value = "DELETE FROM Token WHERE user_id = :userId", nativeQuery = true)
    int deleteByUserId(String userId);
}
