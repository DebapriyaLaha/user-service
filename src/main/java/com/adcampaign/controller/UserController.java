package com.adcampaign.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adcampaign.domain.User;
import com.adcampaign.repository.UserRepository;

@RestController
public class UserController{
	

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/findAll")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/usersave")
	public void save() {
		userRepository.save(new User("Debapriya","Laha","dlaha@kk.com","UK"));
	}
	

}
