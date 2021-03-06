package com.vacations.controllers;
import com.vacations.models.Vacation;
import com.vacations.services.VacationsService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/vacations")
public class VacationController {
	@Autowired
	private VacationsService vacationsService;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Vacation> getAllVacations() {
		List<Vacation> vacations = new ArrayList<>();
		vacationsService.getAllVacations().forEach(vacations::add);
		return vacations;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Vacation getVacationById(@PathVariable Long id) {
		return vacationsService.getVacationsById(id);
	}
	
	@RequestMapping(value = "/bymonth/{offset}", method = RequestMethod.GET)
	public List<Vacation> getVacationByMonth(@PathVariable Integer offset) {
		return vacationsService.getVacationsByMonth(offset);
	}
	
	@RequestMapping(value = "/deletebyuser/{user_id}", method = RequestMethod.DELETE)
	public void deleteVacationsByUserId(@PathVariable Long user_id) {
		vacationsService.deleteVacationsByUserId(user_id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addVacation(@RequestBody Vacation vacation) {
		vacationsService.addVacation(vacation);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateVacation(@PathVariable Long id, @RequestBody Vacation vacation) {
		vacationsService.updateVacation(id, vacation);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteVacation(@PathVariable Long id) {
		vacationsService.deleteVacation(id);
	}

}
