package com.kitchen.iChef.Repository;

import com.kitchen.iChef.Domain.Token;
import com.kitchen.iChef.Repository.Interfaces.ITokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile({"dev", "default"})
public class TokenRepository {

    private final ITokenRepository tokenRepository;

    @Autowired
    public TokenRepository(ITokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    public Optional<Token> findByValue(String value) {
        return tokenRepository.findByValue(value);
    }

    public Optional<Token> findByUserId(String userId) {
        return tokenRepository.findByUserId(userId);
    }

    public int deleteByUserId(String userId) {
        return tokenRepository.deleteByUserId(userId);
    }
}
