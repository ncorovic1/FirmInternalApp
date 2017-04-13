package com.firminternalapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firminternalapp.models.User;
import com.firminternalapp.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;
	
    public List<User> findAll() {
    	List<User> users = userRepository.findAll();
        return users;
    }
    
    public User findOne(long id) {
    	User user = userRepository.findOne(id);
        return user;
    }

    public boolean save(User user){
        try
        {
            userRepository.save(user);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    public boolean update(User user) {
        try {
        	userRepository.save(user);
        	return true;
        } catch (Exception e){
            return false;
        }
    }
    
    public boolean deleteAll() {
        try {
        	userRepository.deleteAll();
        	return true;
        } catch (Exception e){
            return false;
        }
    }
  
    public boolean deleteOne(long id) {
        try {
        	userRepository.delete(id);
        	return true;
        } catch (Exception e){
            return false;
        }
    }	
}