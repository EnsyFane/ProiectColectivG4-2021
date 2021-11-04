package com.kitchen.iChef.Controller.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SignUpRequest {

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SignUpRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
