package com.adcampaign.userservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.adcampaign.config.UserServiceApplication;
import com.adcampaign.controller.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceApplication.class)
public class UserServiceApplicationTests {

	@Autowired
	private UserController controller;
	
	@Test
	public void contextLoads() {
		System.out.println("Starting.....");
		this.controller.save();
		System.out.println(this.controller.retrieveAllUsers());
	}

}
