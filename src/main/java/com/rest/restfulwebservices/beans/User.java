package com.rest.restfulwebservices.beans;

import java.time.LocalDate;

public class User {
	private Long id;
	private String name;
	private int age;
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
