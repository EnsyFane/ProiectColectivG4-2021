package com.kitchen.iChef.Service;

import com.kitchen.iChef.Domain.Token;
import com.kitchen.iChef.Repository.TokenRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

    private static final Logger LOGGER = LogManager.getLogger();

    private final TokenRepository tokenRepository;
    private final Duration duration;

    @Autowired
    public TokenService(TokenRepository tokenRepository, @Value("${token.expiry}") Duration duration) {
        LOGGER.info("Initializing TokenService");
        this.tokenRepository = tokenRepository;
        this.duration = duration;
    }

    public Optional<Token> findTokenByUserId(String userID) {
        LOGGER.info("Find token with the user id {}", userID);
        return tokenRepository.findByUserId(userID);
    }

    public Token findTokenByValue(String value) throws Exception {
        LOGGER.info("Finding token by value");
        return tokenRepository.findByValue(value).orElseThrow(() -> new Exception("The token doesn't exist!"));
    }

    public Boolean deleteTokenByUserId(String userID) {
        LOGGER.info("Delete token with the user id {}", userID);
        return tokenRepository.deleteByUserId(userID) > 0;
    }

    public Token generateValidToken(String userID) {
        Optional<Token> token = findTokenByUserId(userID);
        if (token.isEmpty() || (token.get().isExpired() && deleteTokenByUserId(userID))) {
            return generateToken(userID);
        }
        return token.get();
    }

    private Token generateToken(String userID) {
        LOGGER.info("Generating token for user with the id {}", userID);
        Token token = new Token();
        token.setUserId(userID);
        token.setValue(UUID.randomUUID().toString());
        token.setExpirationDate(ZonedDateTime.now(ZoneId.of("Europe/Bucharest"))
                .plusMinutes(duration.toMinutes())
                .plusHours(duration.toHours()));
        return tokenRepository.save(token);
    }
}
