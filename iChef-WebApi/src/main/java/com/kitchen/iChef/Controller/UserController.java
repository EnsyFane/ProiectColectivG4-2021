package com.kitchen.iChef.Controller;

import com.kitchen.iChef.Controller.Request.AppUserRequest;
import com.kitchen.iChef.Controller.Request.Transformer.AppUserRequestTransformer;
import com.kitchen.iChef.Controller.Response.AppUserResponse;
import com.kitchen.iChef.Controller.Response.Transformer.AppUserResponseTransformer;
import com.kitchen.iChef.Domain.AppUser;
import com.kitchen.iChef.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    //will be removed out of security concerns
    @GetMapping
    public List<AppUserResponse> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(AppUserResponseTransformer::transformToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public AppUserResponse getUserById(@PathVariable String id) {
        return AppUserResponseTransformer.transformToResponse(userService.getUser(id));
    }

    //will be removed
    @PostMapping
    public AppUserResponse addUser(@RequestBody AppUserRequest userRequest) {
        return AppUserResponseTransformer.transformToResponse(userService.addUser(
                AppUserRequestTransformer.transformFromRequest(userRequest)));
    }

    @DeleteMapping(value = "/{id}")
    public AppUserResponse deleteUser(@PathVariable String id) {
        return AppUserResponseTransformer.transformToResponse(userService.deleteUser(id));
    }

    @PutMapping(value = "/{id}")
    public AppUserResponse updateUser(@PathVariable String id, @RequestBody AppUserRequest userRequest) {
        AppUser appUser = AppUserRequestTransformer.transformFromRequest(userRequest);
        appUser.setUserId(id);
        return AppUserResponseTransformer.transformToResponse(userService.updateUser(appUser));
    }
}
