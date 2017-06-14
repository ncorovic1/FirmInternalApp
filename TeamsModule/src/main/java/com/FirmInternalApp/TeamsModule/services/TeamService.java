package com.FirmInternalApp.TeamsModule.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
	
	public void addTeam(String header, Team team) {
		teamRepository.save(team);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", header);
		headers.add("Content-Type", "application/json");
		
		RestTemplate rt = restInit();
		rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		HttpEntity<Team> request = new HttpEntity<Team>(team, headers);

		String usersClient = sirc.getService("users-client");
		String url = usersClient + "/teams";
		
		rt.postForObject(url, request, Team.class);
	}
	
	public void updateTeam(String header, Long id, Team team) {
		Team teamToBeUpdated = teamRepository.findOne(id);
		teamToBeUpdated.setHandle(team.getHandle());
		teamToBeUpdated.setName(team.getName());
		teamToBeUpdated.setInfo(team.getInfo());
		teamRepository.save(teamToBeUpdated);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", header);
		headers.add("Content-Type", "application/json");
		
		RestTemplate rt = restInit();
		rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		HttpEntity<Team> request = new HttpEntity<Team>(teamToBeUpdated, headers);
		
		String usersClient = sirc.getService("users-client");
		String url = usersClient + "/teams/{id}";
		
		rt.put(url, request, team.getId());
	}
	
	public void deleteTeam(String header, Long id) {
	    teamRepository.delete(id);
	    
	    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
	    headers.add("Authorization", header);
	    
	    RestTemplate rt = restInit();
		rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	    HttpEntity<Long> request = new HttpEntity<Long>(id, headers);
	    
		String usersClient = sirc.getService("users-client");
		String url = usersClient + "/teams/{id}";
	    
	    rt.exchange(url, HttpMethod.DELETE, request, String.class, id);
	}
	
}
