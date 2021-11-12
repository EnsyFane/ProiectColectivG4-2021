package com.kitchen.iChef.Controller.Model.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
public class AppUserResponse {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private ZonedDateTime joinedDate;
    private ZonedDateTime lastOnline;
}
