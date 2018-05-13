package com.adcampaign.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.adcampaign.repository.UserRepository;

@SpringBootApplication
@EnableWebMvc
@ComponentScan("com.adcampaign")
@EnableAutoConfiguration
@EnableJpaRepositories (basePackages = "com.adcampaign.repository")
@EntityScan(basePackages = "com.adcampaign.domain")
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
}
