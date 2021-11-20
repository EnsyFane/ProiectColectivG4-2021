package com.kitchen.iChef.Service;

import com.kitchen.iChef.Domain.Token;
import com.kitchen.iChef.Repository.TokenRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.ZonedDateTime;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {
    @InjectMocks
    TokenService tokenService;
    @Mock
    TokenRepository tokenRepository;

    @Test
    @DisplayName("Test findTokenByUserId Success Case")
    void findTokenByUserId_succes()
    {
        Token token = new Token();
        token.setTokenId("1");
        token.setUserId("1");
        token.setExpirationDate(ZonedDateTime.now());
        token.setValue("10");
        Mockito.when(tokenRepository.findByUserId(token.getUserId())).thenReturn(java.util.Optional.of(token));
        tokenService.findTokenByUserId(token.getUserId());
        Mockito.verify(tokenRepository).findByUserId(token.getUserId());
    }

    @Test
    @DisplayName("Test findTokenByUserId Failure Case")
    void findTokenByUserId_failure()
    {
        Token token = new Token();
        token.setTokenId("1");
        token.setUserId("1");
        token.setExpirationDate(ZonedDateTime.now());
        token.setValue("10");
        Mockito.doThrow(new RuntimeException("Testing Failure Case")).
                when(tokenRepository).findByUserId(token.getUserId());
        Assertions.assertThrows(Exception.class, () -> {
            tokenService.findTokenByUserId(token.getUserId());
        });
    }

    @Test
    @DisplayName("Test findTokenByValue Success Case")
    void findTokenByValue_success()
    {
        Token token = new Token();
        token.setTokenId("1");
        token.setUserId("1");
        token.setExpirationDate(ZonedDateTime.now());
        token.setValue("10");
        Mockito.when(tokenRepository.findByValue(token.getValue())).thenReturn(java.util.Optional.of(token));
        try {
            tokenService.findTokenByValue(token.getValue());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Mockito.verify(tokenRepository).findByValue(token.getValue());
    }
    @Test
    @DisplayName("Test findTokenByValue_success Failure Case")
    void findTokenByValue_failure()
    {
        Token token = new Token();
        token.setTokenId("1");
        token.setUserId("1");
        token.setExpirationDate(ZonedDateTime.now());
        token.setValue("10");
        Mockito.doThrow(new RuntimeException("Testing Failure Case")).when(tokenRepository).findByValue(token.getValue());
        Assertions.assertThrows(Exception.class, () -> {
            tokenService.findTokenByValue(token.getValue());
        });
    }

    @Test
    @DisplayName("Test deleteTokenByUserId Success Case")
    void deleteTokenByUserId_success()
    {
        Token token = new Token();
        token.setTokenId("1");
        token.setUserId("2");
        token.setExpirationDate(ZonedDateTime.now());
        token.setValue("10");
        Mockito.when(tokenRepository.deleteByUserId(token.getUserId())).thenReturn(Integer.valueOf(token.getUserId()));
        tokenService.deleteTokenByUserId(token.getUserId());
        Mockito.verify(tokenRepository).deleteByUserId(token.getUserId());
    }
    @Test
    @DisplayName("Test deleteTokenByUserId Failure Case")
    void deleteTokenByUserId_failure()
    {
        Token token = new Token();
        token.setTokenId("1");
        token.setUserId("2");
        token.setExpirationDate(ZonedDateTime.now());
        token.setValue("10");
        Mockito.doThrow(new RuntimeException("Testing Failure Case")).when(tokenRepository).deleteByUserId(token.getUserId());
        Assertions.assertThrows(Exception.class, () -> {
            tokenService.deleteTokenByUserId(token.getUserId());
        });
    }
}
