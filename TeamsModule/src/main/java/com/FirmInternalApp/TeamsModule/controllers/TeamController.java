package com.FirmInternalApp.TeamsModule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.FirmInternalApp.TeamsModule.models.*;
import com.FirmInternalApp.TeamsModule.services.TeamService;

@RestController
@CrossOrigin
@RequestMapping(value = "/teams")
public class TeamController {
	
	@Autowired
	private TeamService teamService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Team> getAllTeams() {
    	return teamService.getAllTeams();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Team getTeamById(@PathVariable Long id) {
    	return teamService.getTeamById(id);
    }
    
    @RequestMapping(value = "/byhandle/{handle}", method = RequestMethod.GET)
    public Team getTeamByHandle(@PathVariable String handle) {
    	return teamService.getTeamByHandle(handle);
    }
    
    @RequestMapping(value = "/byname/{name}", method = RequestMethod.GET)
    public Team getTeamByName(@PathVariable String name) {
    	return teamService.getTeamByName(name);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void addTeam(@RequestHeader("Authorization") String header, @RequestBody Team team) {
    	teamService.addTeam(header, team);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateTeam(@RequestHeader("Authorization") String header, @PathVariable Long id, @RequestBody Team team) {
    	teamService.updateTeam(header, id, team);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTeam(@RequestHeader("Authorization") String header, @PathVariable Long id) {
    	teamService.deleteTeam(header, id);
    }
    
}
