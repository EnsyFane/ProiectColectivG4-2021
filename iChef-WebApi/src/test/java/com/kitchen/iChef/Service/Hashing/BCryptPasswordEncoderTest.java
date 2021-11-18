package com.kitchen.iChef.Service.Hashing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BCryptPasswordEncoderTest {
    @Test
    @DisplayName("Test match Success Case")
    void match_success()
    {
        String password = "popcorn";
        String hash_password = BCryptPasswordEncoder.hash(password);
        Assertions.assertTrue(BCryptPasswordEncoder.match(password,hash_password));
    }
    @Test
    @DisplayName("Test match Success Case")
    void match_failure()
    {
        String password = "popcorn";
        String wrong_password = "wrong";
        String hash_password = BCryptPasswordEncoder.hash(password);
        Assertions.assertThrows(Exception.class, () -> {
            BCryptPasswordEncoder.match(null,hash_password);
        });
        Assertions.assertFalse(BCryptPasswordEncoder.match(wrong_password,hash_password));
    }
}
