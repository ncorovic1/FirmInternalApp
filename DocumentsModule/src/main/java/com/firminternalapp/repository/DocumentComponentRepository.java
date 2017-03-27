package com.firminternalapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.firminternalapp.models.DocumentComponent;

@RepositoryRestResource
public interface DocumentComponentRepository extends JpaRepository<DocumentComponent, Long> {

}