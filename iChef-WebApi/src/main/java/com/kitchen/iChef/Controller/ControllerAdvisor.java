package com.kitchen.iChef.Controller;


import com.kitchen.iChef.Exceptions.ResourceNotFoundException;
import com.kitchen.iChef.Exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    /***
     * This method is triggered when the requested resource is not found
     * (checking or deleting a user with an id that is not stored in the database )
     * @param e - the thrown exception
     * @return http status: NOT FOUND and a suggestive message
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    /***
     * This method is triggered when the data provided by the user are invalid
     * (creating or updating a user with invalid data)
     * @param e - the thrown exception
     * @return http status: BAD REQUEST and a suggestive message
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
