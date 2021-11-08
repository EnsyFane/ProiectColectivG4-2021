package com.kitchen.iChef.Service;

import com.kitchen.iChef.Domain.AppUser;
import com.kitchen.iChef.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService() {
    }

    public AppUser addUser(AppUser user) {
        return userRepository.save(user);
    }

    public AppUser getUser(String id) {
        return userRepository.findOne(id);
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public AppUser deleteUser(String id) {
        return userRepository.delete(id);
    }

    public AppUser updateUser(AppUser user) {
        return userRepository.update(user);
    }

    public void signUp(AppUser appUser) throws Exception {
        if (userRepository.findByUsername(appUser.getUsername()).isPresent() || userRepository.findByEmail(appUser.getEmail()).isPresent()) {
            throw new Exception("User already exists!");
        }
        userRepository.save(appUser);
    }
}
