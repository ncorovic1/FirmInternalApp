package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.example.models.User;

@Repository
public abstract class UsersRepository implements CrudRepository<User, Long> {

}
