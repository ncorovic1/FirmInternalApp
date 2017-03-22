package com.vacations.controllers;
import com.vacations.models.Vacation;
import com.vacations.repository.VacationRepository;
import com.vacations.repository.VacationTypesRepository;

import java.util.List;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/vacations")
public class VacationTypeController {

	@Autowired
    private VacationTypesRepository vacationTypesRepository;
	
	@RequestMapping(value= "/", method = RequestMethod.GET)
    public List<Vacation> getAll() {
    	List<Vacation> users = vacationTypesRepository.getAll();
        return users;
    }
    
    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public List<Vacation> getAllBy(@PathVariable("id") int id) {
    	List<Vacation> vacation =  vacationTypesRepository.getAllBy(id);
        return vacation;
    }


    @RequestMapping(method = RequestMethod.POST)
    public boolean createVacation(@RequestParam Date beginDate, Date endDate, int userId, int vacationType){
    	Vacation vacation=new Vacation();
    	vacation.setBeginDate(beginDate);
    	vacation.setEndDate(endDate);
        try
        {
        	vacationTypesRepository.createVacation(beginDate, endDate, userId, vacationType);
            return true;
        } 
        catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value = "/delete", method= RequestMethod.POST)
    public boolean deleteVacation(@RequestParam int id) {
        try {
        	vacationTypesRepository.deleteVacation(id);
        	return true;
        }catch (Exception e){
            return false;
        }
    }

   
}
