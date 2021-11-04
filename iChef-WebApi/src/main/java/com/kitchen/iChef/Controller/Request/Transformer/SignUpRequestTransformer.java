package com.kitchen.iChef.Controller.Request.Transformer;

import com.kitchen.iChef.Controller.Request.SignUpRequest;
import com.kitchen.iChef.Domain.AppUser;
import com.kitchen.iChef.Service.Hashing.BCryptPasswordEncoder;

import java.time.ZonedDateTime;

public class SignUpRequestTransformer {

    public static AppUser transformFromRequest(SignUpRequest signUpRequest) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(signUpRequest.getFirstName());
        appUser.setLastName(signUpRequest.getLastName());
        appUser.setUsername(signUpRequest.getUsername());
        appUser.setEmail(signUpRequest.getEmail());
        appUser.setJoinedDate(ZonedDateTime.now());
        appUser.setLastOnline(ZonedDateTime.now());
        appUser.setIsAdmin(false);
        appUser.setHashedPassword(BCryptPasswordEncoder.hash(signUpRequest.getPassword()));
        return appUser;
    }

}
