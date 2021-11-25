package com.kitchen.iChef.Controller;

import com.kitchen.iChef.Mapper.AppUserMapper;
import com.kitchen.iChef.Controller.Model.Request.AppUserRequest;
import com.kitchen.iChef.Controller.Model.Response.AppUserResponse;
import com.kitchen.iChef.Domain.AppUser;
import com.kitchen.iChef.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AppUserMapper appUserMapper;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        this.appUserMapper = new AppUserMapper();
    }

    //will be removed out of security concerns
    @GetMapping
    public List<AppUserResponse> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(appUserMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public AppUserResponse getUserById(@PathVariable String id) {
        return appUserMapper.mapToResponse(userService.getUser(id));
    }

    //will be removed
    @PostMapping
    public AppUserResponse addUser(@RequestBody AppUserRequest userRequest) {
        return appUserMapper.mapToResponse(userService.addUser(
                appUserMapper.mapFromRequest(userRequest)));
    }

    @DeleteMapping(value = "/{id}")
    public AppUserResponse deleteUser(@PathVariable String id) {
        return appUserMapper.mapToResponse(userService.deleteUser(id));
    }

    @PutMapping(value = "/{id}")
    public AppUserResponse updateUser(@PathVariable String id, @RequestBody AppUserRequest userRequest) {
        AppUser appUser = appUserMapper.mapFromRequest(userRequest);
        appUser.setUserId(id);
        return appUserMapper.mapToResponse(userService.updateUser(appUser));
    }
}
