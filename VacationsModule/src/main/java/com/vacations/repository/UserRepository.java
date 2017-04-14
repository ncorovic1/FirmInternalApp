package com.vacations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.vacations.models.User;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
}