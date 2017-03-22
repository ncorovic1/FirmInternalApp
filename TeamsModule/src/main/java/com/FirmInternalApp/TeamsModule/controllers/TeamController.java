package com.FirmInternalApp.TeamsModule.controllers;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.FirmInternalApp.TeamsModule.models.*;
import com.FirmInternalApp.TeamsModule.repositories.TeamRepository;

@RestController
@RequestMapping(value = "/teamsctrl")
public class TeamController {
	
	private TeamRepository repo;

	@RequestMapping("/helloteam")
	public String hello() {
		return "Hello team!";
	}
	

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<Team> getAll() {
    	Iterable<Team> teams = repo.findAll();
        return teams;
    }
}
