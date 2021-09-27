package com.rest.restfulwebservices.beans;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=2,message="Name should be atleast two characters long")
	private String name;
	
	private int age;
	
	@Past(message="Date of Birth should be past date")
	private LocalDate dob;
	
	public User() {
		super();
	}
	public User(Long id, String name, int age, LocalDate dob) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.dob = dob;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
}
