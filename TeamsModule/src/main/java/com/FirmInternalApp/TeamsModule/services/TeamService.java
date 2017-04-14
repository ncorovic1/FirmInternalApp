package com.FirmInternalApp.TeamsModule.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.FirmInternalApp.TeamsModule.models.Team;
import com.FirmInternalApp.TeamsModule.repositories.TeamRepository;
import com.FirmInternalApp.TeamsModule.controllers.*;

@Service
public class TeamService {
	
	@Autowired
	ServiceInstanceRestController sirc;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Bean
	@LoadBalanced
	private RestTemplate restInit() {
		return new RestTemplate();
	}
	
	public List<Team> getAllTeams() {
		List<Team> teams = new ArrayList<>();
		teamRepository.findAll().forEach(teams::add);
		return teams;
	}
	
	public Team getTeamById(Long id) {
		return teamRepository.findOne(id);
	}
	
	public Team getTeamByHandle(String handle) {
		return teamRepository.findByHandle(handle);
	}
	
	public Team getTeamByName(String name) {
		return teamRepository.findByName(name);
	}
	
	public void addTeam(Team team) {
		teamRepository.save(team);
		
		String usersClient = sirc.getService("users-client");
		
		String url = usersClient + "/teams";
		
		//String url = "http://localhost:8085/teams";
		RestTemplate rt = restInit();	
		rt.postForObject(url, team, Team.class);
	}
	
	public void updateTeam(Long id, Team team) {
		Team teamToBeUpdated = teamRepository.findOne(id);
		teamToBeUpdated.setHandle(team.getHandle());
		teamToBeUpdated.setName(team.getName());
		teamToBeUpdated.setInfo(team.getInfo());
		teamRepository.save(teamToBeUpdated);
		
		String usersClient = sirc.getService("users-client");
		
		String url = usersClient + "/teams/{id}";
		
		//String url = "http://localhost:8085/teams/{id}";
		RestTemplate rt = restInit();
		rt.put(url, team, team.getId());
	}
	
	public void deleteTeam(Long id) {
		teamRepository.delete(id);
		
		String usersClient = sirc.getService("users-client");
		String url = usersClient + "/teams/{id}";
		
	//	String url = "http://localhost:8085/users/{id}";
	     
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", String.valueOf(id));
	     
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.delete(url,  params);
	}
	
}
