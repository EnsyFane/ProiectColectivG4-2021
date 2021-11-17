package com.kitchen.iChef.Exceptions;

/****
 *  AuthenticationException is thrown when the user credentials are invalid
 */
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}
