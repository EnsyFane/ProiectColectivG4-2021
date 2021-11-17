package com.kitchen.iChef.Exceptions;

/***
 * ValidationException should be thrown when the data received from a user is invalid
 */
public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
