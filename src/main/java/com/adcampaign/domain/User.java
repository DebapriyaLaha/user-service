package com.adcampaign.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "domain")
public class User {
	
	@Id
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	@Indexed(unique = true)
	private String email;
	
	private String address;
	
	public User() {}
		
	public User(String firstName, String lastName, String email, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String secondName) {
		this.lastName = secondName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", secondName=" + lastName + ", email=" + email
				+ ", address=" + address + "]";
	}
	
	

}
