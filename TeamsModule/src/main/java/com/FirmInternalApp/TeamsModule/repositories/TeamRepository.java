package com.FirmInternalApp.TeamsModule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.FirmInternalApp.TeamsModule.models.*;

@RepositoryRestResource(collectionResourceRel = "teamsrepository", path = "teamsrepository")
public interface TeamRepository extends JpaRepository<Team, Long> {
	
	Team findByHandle(String handle);
	
	Team findByName(String name);
	
}