package com.kitchen.iChef.Service;

import com.kitchen.iChef.Domain.AppUser;
import com.kitchen.iChef.Domain.Token;
import com.kitchen.iChef.Exceptions.AuthenticationException;
import com.kitchen.iChef.Exceptions.ResourceNotFoundException;
import com.kitchen.iChef.Exceptions.ValidationException;
import com.kitchen.iChef.Repository.UserRepository;
import com.kitchen.iChef.Service.Hashing.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    public UserService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public AppUser addUser(AppUser user) {
        return userRepository.save(user);
    }

    public AppUser getUser(String id) {
        AppUser appUser;
        try {
            appUser = userRepository.findOne(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("No user with this id");
        }
        return appUser;
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public AppUser deleteUser(String id) {
        AppUser appUser;
        try {
            appUser = userRepository.delete(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("No user with this id");
        }
        return appUser;
    }

    public AppUser updateUser(AppUser user) {
        AppUser appUser;
        try {
            appUser = userRepository.update(user);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("No user with this id");
        }
        return appUser;
    }

    public void signUp(AppUser appUser) throws ValidationException {
        if (userRepository.findByUsername(appUser.getUsername()).isPresent() || userRepository.findByEmail(appUser.getEmail()).isPresent()) {
            throw new ValidationException("User already exists!");
        }
        userRepository.save(appUser);
    }

    public Token login(String email, String password) throws AuthenticationException {
        Optional<AppUser> user = userRepository.findByEmail(email);

        if (!user.isPresent() || !BCryptPasswordEncoder.match(password, user.get().getHashedPassword())) {
            throw new AuthenticationException("Invalid credentials!");
        }
        return tokenService.generateValidToken(user.get().getUserId());
    }
}
