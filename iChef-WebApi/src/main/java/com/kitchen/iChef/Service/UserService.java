package com.kitchen.iChef.Service;

import com.kitchen.iChef.Domain.AppUser;
import com.kitchen.iChef.Domain.Token;
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

    public Token login(String email, String password) throws Exception {
        Optional<AppUser> user = userRepository.findByEmail(email);

        if (!user.isPresent() || !BCryptPasswordEncoder.match(password, user.get().getHashedPassword())) {
            throw new Exception("Invalid credentials!");
        }
        return tokenService.generateValidToken(user.get().getUserId());
    }
}
