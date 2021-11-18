package com.kitchen.iChef.Controller;


import com.kitchen.iChef.Exceptions.AuthenticationException;
import com.kitchen.iChef.Exceptions.ResourceNotFoundException;
import com.kitchen.iChef.Exceptions.ValidationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor {

    /***
     * This method is triggered when the requested resource is not found
     * (checking or deleting a user with an id that is not stored in the database )
     * @param e - the thrown exception
     * @return http status: NOT FOUND and a suggestive message
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    /***
     * This method is triggered when the data provided by the user is invalid
     * (creating or updating a user with invalid data)
     * @param e - the thrown exception
     * @return http status: BAD REQUEST and a suggestive message
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /***
     * This method is triggered when the user credentials are invalid
     * (login)
     * @param e - the thrown exception
     * @return http status: UNAUTHORIZED and a suggestive message
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    /***
     * This method is triggered when the arguments given by the user are invalid
     * (signup,login)
     * @param e - the thrown exception
     * @return http status: BAD_REQUEST and a suggestive message
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errorMessages = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(errorMessages.toString(), HttpStatus.BAD_REQUEST);
    }
}