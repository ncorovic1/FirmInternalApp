package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.models.*;
import com.example.services.TeamService;

@RestController
@CrossOrigin
@RequestMapping(value = "/teamsController")
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
}
