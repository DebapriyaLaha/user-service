package com.adcampaign.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id",unique=true,nullable=false)
	private Integer id;
	
	@Column(name="user_firstname",nullable=false)
	private String firstName;
	
	@Column(name="user_lastname",nullable=false)
	private String lastName;
	
	@Column(name="user_email")
	private String email;
	
	@Column(name="user_address")
	private String address;
	
	public User() {}
		
	public User(String firstName, String lastName, String email, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
