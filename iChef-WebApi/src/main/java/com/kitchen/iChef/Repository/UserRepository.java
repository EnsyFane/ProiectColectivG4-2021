package com.kitchen.iChef.Repository;

import com.kitchen.iChef.Domain.AppUser;
import com.kitchen.iChef.Repository.Interfaces.ICrudRepository;
import com.kitchen.iChef.Repository.Interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile({"dev", "default"})
public class UserRepository implements ICrudRepository<AppUser, String> {

    private final IUserRepository iUserRepository;

    @Autowired
    public UserRepository(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public AppUser findOne(String s) {
        if (iUserRepository.findById(s).isPresent()) {
            return iUserRepository.findById(s).get();
        }
        return null;
    }

    @Override
    public List<AppUser> findAll() {
        List<AppUser> allUsers = new ArrayList<>();
        Iterable<AppUser> users = iUserRepository.findAll();
        for (AppUser user : users) {
            allUsers.add(user);
        }
        return allUsers;
    }

    @Override
    public AppUser save(AppUser entity) {
        return iUserRepository.save(entity);
    }

    @Override
    public AppUser delete(String s) {
        AppUser user = findOne(s);
        iUserRepository.deleteById(s);
        return user;
    }

    @Override
    public AppUser update(AppUser entity) {
        iUserRepository.deleteById(entity.getUserId());
        iUserRepository.save(entity);
        return entity;
    }

    public Optional<AppUser> checkIfExists(String username, String email) {
        return iUserRepository.checkIfExists(username, email);
    }

    public Optional<AppUser> findUserByEmail(String email) {
        return iUserRepository.findUserByEmail(email);
    }
}
