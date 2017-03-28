package com.FirmInternalApp.TeamsModule.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FirmInternalApp.TeamsModule.models.Team;
import com.FirmInternalApp.TeamsModule.repositories.TeamRepository;

@Service
public class TeamService {
	
	@Autowired
	private TeamRepository teamRepository;
	
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
	}
	
	public void updateTeam(Long id, Team team) {
		Team teamToBeUpdated = teamRepository.findOne(id);
		teamToBeUpdated.setHandle(team.getHandle());
		teamToBeUpdated.setName(team.getName());
		teamToBeUpdated.setInfo(team.getInfo());
		teamRepository.save(teamToBeUpdated);
	}
	
	public void deleteTeam(Long id) {
		teamRepository.delete(id);
	}
	
}
