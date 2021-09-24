package com.rest.restfulwebservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.restfulwebservices.beans.User;
import com.rest.restfulwebservices.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping(path="/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping(path="/users/{id}")
	public User getUser(@PathVariable int id) {
		return userService.getUser(id);
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
}
