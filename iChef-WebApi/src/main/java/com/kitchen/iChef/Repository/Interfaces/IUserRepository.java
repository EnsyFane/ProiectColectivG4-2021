package com.kitchen.iChef.Repository.Interfaces;

import com.kitchen.iChef.Domain.AppUser;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;


@Profile("!test")
public interface IUserRepository extends CrudRepository<AppUser, String> {
}
