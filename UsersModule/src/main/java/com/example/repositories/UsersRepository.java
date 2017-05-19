package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.models.User;

@RepositoryRestResource(collectionResourceRel = "usersrepository", path = "usersrepository")
public interface UsersRepository extends JpaRepository<User, Long> {
	
	User findById(long id);

	User findByFirstName(String name);
	
	User findByEmail(String email);
	
	User findByUsername(String username);
	
	List<User> findByTeamId(long teamId);
}
