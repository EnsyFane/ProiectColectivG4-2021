package com.kitchen.iChef.Controller.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class AppUserRequest {

    @NotBlank(message = "The first name of the user is not valid!")
    private String firstName;

    @NotBlank(message = "The last name of the user is not valid!")
    private String lastName;

    @NotBlank(message = "The username of the user is not valid!")
    private String username;

    @NotNull(message = "The email of the user is not valid!")
    @Pattern(regexp = "^[a-zA-Z]+[a-zA-Z0-9.]*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
            message = "The email of the user is not valid!")
    private String email;

    @NotBlank(message = "The password is not valid!")
    private String password;
}