package com.kitchen.iChef.Controller.Response.Transformer;

import com.kitchen.iChef.Controller.Response.AppUserResponse;
import com.kitchen.iChef.Domain.AppUser;

public class AppUserResponseTransformer {

    public static AppUserResponse transformToResponse(AppUser appUser) {
        AppUserResponse appUserResponse = new AppUserResponse();
        appUserResponse.setFirstName(appUser.getFirstName());
        appUserResponse.setLastName(appUser.getLastName());
        appUserResponse.setUsername(appUserResponse.getUsername());
        appUserResponse.setEmail(appUserResponse.getEmail());
        appUserResponse.setJoinedDate(appUser.getJoinedDate());
        appUserResponse.setLastOnline(appUser.getLastOnline());
        return appUserResponse;
    }
}
