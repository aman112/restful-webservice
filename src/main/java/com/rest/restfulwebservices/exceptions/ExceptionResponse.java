package com.rest.restfulwebservices.exceptions;

import java.time.LocalDateTime;

public class ExceptionResponse {
	private LocalDateTime timeStamp;
	private String message;
	private String details;
	
	public ExceptionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExceptionResponse(LocalDateTime timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}