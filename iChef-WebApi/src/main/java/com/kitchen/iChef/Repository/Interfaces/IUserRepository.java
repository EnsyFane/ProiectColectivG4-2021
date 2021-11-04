package com.kitchen.iChef.Repository.Interfaces;

import com.kitchen.iChef.Domain.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<AppUser, String> {
    @Query(
            value = "SELECT * FROM app_user WHERE username = :username OR email = :email",
            nativeQuery = true)
    Optional<AppUser> checkIfExists(@Param("username")String username,@Param("email") String email);

    @Query(
            value = "SELECT * FROM app_user WHERE email = :email",
            nativeQuery = true
    )
    Optional<AppUser> findUserByEmail(@Param("email")String email);
}
