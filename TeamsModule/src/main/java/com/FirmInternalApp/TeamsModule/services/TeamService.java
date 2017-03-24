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
	
	public void addTeam(Team team) {
		teamRepository.save(team);
	}
	
	public void updateTeam(Team team) {
		teamRepository.save(team);
	}
	
	public void deleteTeam(Long id) {
		teamRepository.delete(id);
	}
	
}
