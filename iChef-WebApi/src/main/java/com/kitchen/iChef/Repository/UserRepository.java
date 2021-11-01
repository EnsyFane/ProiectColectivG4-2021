package com.kitchen.iChef.Repository;

import com.kitchen.iChef.Domain.AppUser;
import com.kitchen.iChef.Repository.Interfaces.ICrudRepository;
import com.kitchen.iChef.Repository.Interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile({"dev", "default"})
public class UserRepository implements ICrudRepository<AppUser, String> {

    @Autowired
    IUserRepository iUserRepository;

    @Override
    public AppUser findOne(String s) {
        return iUserRepository.findById(s).get();
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
}
