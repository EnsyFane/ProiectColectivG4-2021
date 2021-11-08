package com.kitchen.iChef.Controller.Request.Transformer;

import com.kitchen.iChef.Controller.Request.AppUserRequest;
import com.kitchen.iChef.Domain.AppUser;

public class AppUserRequestTransformer {

    public static AppUser transformFromRequest(AppUserRequest appUserRequest) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(appUserRequest.getFirstName());
        appUser.setLastName(appUserRequest.getLastName());
        appUser.setUsername(appUserRequest.getUsername());
        appUser.setEmail(appUserRequest.getEmail());
        appUser.setHashedPassword(appUserRequest.getPassword());
        return appUser;
    }
}
