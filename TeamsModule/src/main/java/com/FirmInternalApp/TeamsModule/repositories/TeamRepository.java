package com.FirmInternalApp.TeamsModule.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.FirmInternalApp.TeamsModule.models.*;

@RepositoryRestResource(collectionResourceRel = "teams", path = "teamsrepo")
public interface TeamRepository extends CrudRepository<Team, Integer> {
	
	@Override
    Iterable<Team> findAll();
	
	List<Team> findByName(@Param("name") String name);
}

