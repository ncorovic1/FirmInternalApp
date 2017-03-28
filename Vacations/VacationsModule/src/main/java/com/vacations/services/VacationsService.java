package com.vacations.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacations.models.Vacation;
import com.vacations.repository.VacationsRepository;

@Service
public class VacationsService {
	
	@Autowired
	private VacationsRepository vacationsRepository;

	public List<Vacation> getAllVacations() {
		List<Vacation> vacations = new ArrayList<>();
		vacationsRepository.findAll().forEach(vacations::add);
		return vacations;
	}
	
	public Vacation getVacationsById(Long id) {
		return vacationsRepository.findOne(id);
	}
	
	public void addVacation(Vacation user) {
		vacationsRepository.save(user);
	}
	
	public void updateVacation(Vacation user) {
		vacationsRepository.save(user);
	}
	
	public void deleteVacation(Long id) {
		vacationsRepository.delete(id);
	}
	
}
