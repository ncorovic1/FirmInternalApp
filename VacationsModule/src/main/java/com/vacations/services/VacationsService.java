package com.vacations.services;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

	public void deleteVacationsByUserId(Long user_id) {
		vacationsRepository.deleteByUserId(user_id);
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

	public List<Vacation> getVacationsByMonth(Integer offset) {

		ZoneId zoneId = ZoneId.of("Europe/Sarajevo");
		YearMonth yearMonth = YearMonth.now(zoneId).plusMonths(offset);
		LocalDate firstOfMonth = yearMonth.atDay(1);
		LocalDate lastOfMonth = yearMonth.atEndOfMonth();

		ZonedDateTime first = firstOfMonth.atStartOfDay(zoneId);
		ZonedDateTime last = lastOfMonth.atStartOfDay(zoneId);
		Instant firstInstant = first.toInstant();
		Instant lastInstant = last.toInstant();
		java.util.Date firstDay = java.util.Date.from(firstInstant);
		java.util.Date lastDay = java.util.Date.from(lastInstant);

		List<Vacation> allVacations = this.getAllVacations();
		List<Vacation> vacations = new ArrayList<>();

		for (Vacation vacation : allVacations) {
			if (vacation.getBeginDate().before(lastDay) && vacation.getBeginDate().after(firstDay)) {
				vacations.add(vacation);
			}
		}

		return vacations;
	}

}
