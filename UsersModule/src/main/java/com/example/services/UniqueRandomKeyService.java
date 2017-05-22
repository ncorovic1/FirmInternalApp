package com.example.services;

import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.UniqueRandomKey;
import com.example.models.User;
import com.example.repositories.UniqueRandomKeyRepository;

@Service
public class UniqueRandomKeyService {
	
	@Autowired
    private UniqueRandomKeyRepository urkRepository;	
	
	@Autowired
	private UsersService usersService;
	
    public List<UniqueRandomKey> findAll() {
    	List<UniqueRandomKey> urks = urkRepository.findAll();
        return urks;
    }
    
    public UniqueRandomKey findByValue(String value) {
		return urkRepository.findByValue(value);
	}
    
    public UniqueRandomKey findLastByUserId(long user_id) {
		return urkRepository.findFirstByUserIdOrderByIdDesc(user_id);
	}

    public String save(String email){
        try
        {
        	User u = usersService.getUserByEmail(email);
        	if (u == null)
                return "{\"success\":0}";
        	UniqueRandomKey urk = new UniqueRandomKey();
        	urk.setUser(u);
        	urk.setValue(RandomStringUtils.randomAlphanumeric(10));
            urkRepository.save(urk);
            return "{\"success\":1}";
        } catch (Exception e){
        	return "{\"success\":0}";
        }
    }
    
    public boolean update(UniqueRandomKey urk) {
        try {
        	urkRepository.save(urk);
        	return true;
        } catch (Exception e){
            return false;
        }
    }
    
    public boolean deleteByUserId(long id) {
        try {
        	urkRepository.deleteByUserId(id);
        	return true;
        } catch (Exception e){
            return false;
        }
    }
    
    public String validateUrk(String urk, String email) {
    	try {
	    	UniqueRandomKey urkObj = findByValue(urk);
 	
	    	if (urkObj == null || !urkObj.getUser().getEmail().equals(email))
	    		 return "{\"success\":0}";
	    	
	    	deleteByUserId(urkObj.getUser().getId());
	    	return "{\"success\":1}";
    	} catch(Exception e) {
    		return "{\"success\":0}";
    	}
    }
}