package com.rest.restfulwebservices.beans;

public class HelloWorldBean {

	private String message;
	
	public HelloWorldBean() {
		super();
	}
	public HelloWorldBean(String message) {
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
