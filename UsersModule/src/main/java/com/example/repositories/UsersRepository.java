package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.example.models.User;

@RepositoryRestResource(collectionResourceRel = "usersrepository", path = "usersrepository")
public interface UsersRepository extends JpaRepository<User, Long> {

	User findByName(String name);
	
	User findByEmail(String email);
}
