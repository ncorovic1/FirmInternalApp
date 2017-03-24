package com.FirmInternalApp.TeamsModule.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.FirmInternalApp.TeamsModule.models.*;
import com.FirmInternalApp.TeamsModule.services.TeamService;

@RestController
public class TeamController {
	
	@Autowired
	private TeamService teamService;

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public Iterable<Team> getAllTeams() {
    	List<Team> teams = new ArrayList<>();
    	teamService.getAllTeams().forEach(teams::add);
    	return teams;
    }
    
    @RequestMapping(value = "/teams/{id}", method = RequestMethod.GET)
    public Team getTeamById(@PathVariable Long id) {
    	return teamService.getTeamById(id);
    }
    
    @RequestMapping(value = "/teams", method = RequestMethod.POST)
    public void addTeam(@RequestBody Team team) {
    	teamService.addTeam(team);
    }
    
    @RequestMapping(value = "/teams/{id}", method = RequestMethod.PUT)
    public void updateTeam(@PathVariable Long id, @RequestBody Team team) {
    	teamService.updateTeam(team);
    }
    
    @RequestMapping(value = "/teams/{id}", method = RequestMethod.DELETE)
    public void deleteTeam(@PathVariable Long id) {
    	teamService.deleteTeam(id);
    }
    
}
