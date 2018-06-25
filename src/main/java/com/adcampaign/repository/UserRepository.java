package com.adcampaign.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.adcampaign.domain.User;

@Component
public interface  UserRepository extends MongoRepository<User, String>{

	List<User> findByLastName(String lastName);
	
}
