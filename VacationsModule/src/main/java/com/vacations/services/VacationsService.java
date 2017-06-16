package com.vacations.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacations.models.User;
import com.vacations.models.Vacation;
import com.vacations.repository.UserRepository;
import com.vacations.repository.VacationsRepository;

@Service
public class VacationsService {

	@Autowired
	private VacationsRepository vacationsRepository;
	
	@Autowired
	private UserRepository usersRepository;

	public List<Vacation> getAllVacations() {
		List<Vacation> vacations = new ArrayList<>();
		vacationsRepository.findAll().forEach(vacations::add);
		return vacations;
	}

	public Vacation getVacationsById(Long id) {
		return vacationsRepository.findOne(id);
	}

	public void addVacation(Vacation vacation) {
		User user = usersRepository.findOne(vacation.getUser().getId());
		
		if (user != null) {
			vacationsRepository.save(vacation);
		}
	}

	public void updateVacation(Long id, Vacation vacation) {
		vacation.setId(id);
		vacationsRepository.save(vacation);
	}

	public void deleteVacation(Long id) {
		vacationsRepository.delete(id);
	}

	public List<Vacation> getVacationByType(long id) {
		return vacationsRepository.findByVacationTypeId(id);
	}

	public Vacation getVacationByBeginDate(Date date) {
		return vacationsRepository.findByBeginDate(date);

	}

}
