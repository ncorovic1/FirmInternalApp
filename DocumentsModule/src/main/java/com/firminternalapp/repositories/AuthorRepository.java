package com.firminternalapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.firminternalapp.models.Author;

@RepositoryRestResource
public interface AuthorRepository extends JpaRepository<Author, Long> {
}