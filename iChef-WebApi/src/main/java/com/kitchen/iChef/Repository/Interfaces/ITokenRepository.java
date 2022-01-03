package com.kitchen.iChef.Repository.Interfaces;

import com.kitchen.iChef.Domain.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ITokenRepository extends CrudRepository<Token, String> {

    Optional<Token> findByValue(String value);

    Optional<Token> findByUserId(String userId);

    long deleteByUserId(String userId);
}
