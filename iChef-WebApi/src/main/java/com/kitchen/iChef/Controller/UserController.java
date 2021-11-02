package com.kitchen.iChef.Controller;

import com.kitchen.iChef.Domain.AppUser;
import com.kitchen.iChef.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    //will be removed out of security concerns
    @GetMapping
    public List<AppUser> getAllUsers() {
        List<AppUser> usersResult = userService.getAllUsers();
        return usersResult;
    }


    @GetMapping(value = "/{id}")
    public AppUser getUserById(@PathVariable String id) {
        AppUser userResult = userService.getUser(id);
        return userResult;
    }

    @PostMapping
    public AppUser addUser(@RequestBody AppUser user) {
        AppUser userResult = userService.addUser(user);
        return userResult;
    }

    @DeleteMapping(value = "/{id}")
    public AppUser deleteUser(@PathVariable String id) {
        AppUser userResult = userService.deleteUser(id);
        return userResult;
    }

    @PutMapping(value = "/{id}")
    public AppUser updateUser(@PathVariable String id, @RequestBody AppUser user) {
        user.setUserId(id);
        AppUser userResult = userService.updateUser(user);
        return userResult;
    }
}
