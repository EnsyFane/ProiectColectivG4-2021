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
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}")
    public AppUser getUserById(@PathVariable String id) {
        return userService.getUser(id);
    }

    //will be removed
    @PostMapping
    public AppUser addUser(@RequestBody AppUser user) {
        return userService.addUser(user);
    }

    @DeleteMapping(value = "/{id}")
    public AppUser deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    @PutMapping(value = "/{id}")
    public AppUser updateUser(@PathVariable String id, @RequestBody AppUser user) {
        user.setUserId(id);
        return userService.updateUser(user);
    }
}
