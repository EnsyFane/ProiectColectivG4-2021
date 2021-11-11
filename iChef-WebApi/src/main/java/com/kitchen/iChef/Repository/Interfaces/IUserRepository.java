package com.kitchen.iChef.Repository.Interfaces;

import com.kitchen.iChef.Domain.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<AppUser, String> {
    @Query(value = "SELECT * FROM app_user WHERE username = :username", nativeQuery = true)
    Optional<AppUser> findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM app_user WHERE email = :email", nativeQuery = true)
    Optional<AppUser> findByEmail(@Param("email") String email);
}
