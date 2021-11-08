package com.kitchen.iChef.Controller.Mapper;

import com.kitchen.iChef.Controller.Model.Request.AppUserRequest;
import com.kitchen.iChef.Controller.Model.Response.AppUserResponse;
import com.kitchen.iChef.Domain.AppUser;

public class AppUserMapper {

    public static AppUser mapFromRequest(AppUserRequest appUserRequest) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(appUserRequest.getFirstName());
        appUser.setLastName(appUserRequest.getLastName());
        appUser.setUsername(appUserRequest.getUsername());
        appUser.setEmail(appUserRequest.getEmail());
        appUser.setHashedPassword(appUserRequest.getPassword());
        return appUser;
    }

    public static AppUserResponse mapToResponse(AppUser appUser) {
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
