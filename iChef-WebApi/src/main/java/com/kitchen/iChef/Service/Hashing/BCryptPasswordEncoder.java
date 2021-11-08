package com.kitchen.iChef.Service.Hashing;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class BCryptPasswordEncoder {

    private static final int STRENGTH = 12; // Any value between 10-31 is fine

    private BCryptPasswordEncoder() {

    }

    public static String hash(String plainTextPassword) {
        if (plainTextPassword == null) {
            throw new IllegalArgumentException("The password to hash can't be null!");
        }
        char[] hashChars = BCrypt.withDefaults().hashToChar(STRENGTH, plainTextPassword.toCharArray());
        return new String(hashChars);
    }

    public static boolean match(String plainTextPassword, String hash) {
        if (plainTextPassword == null || hash == null) {
            throw new IllegalArgumentException("The passwords can't be null!");
        }
        return BCrypt.verifyer().verify(plainTextPassword.toCharArray(), hash.toCharArray()).verified;
    }
}
