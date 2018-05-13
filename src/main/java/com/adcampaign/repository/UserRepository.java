package com.adcampaign.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adcampaign.domain.User;

@Repository
@Transactional
public interface  UserRepository extends JpaRepository<User, Integer>{

	List<User> findByLastName(String lastName);
	
}
