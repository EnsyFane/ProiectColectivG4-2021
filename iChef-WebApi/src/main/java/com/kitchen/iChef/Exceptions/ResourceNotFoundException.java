package com.kitchen.iChef.Exceptions;

/****
 * ResourceNotFoundException is thrown when a resource is not found
 */
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
