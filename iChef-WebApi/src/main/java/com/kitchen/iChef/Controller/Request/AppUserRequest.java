package com.kitchen.iChef.Controller.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppUserRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
