package com.rest.restfulwebservices.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@Autowired
	MessageSource messageSource;
	
	@GetMapping(path="/users")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users= userService.getAllUsers();
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@GetMapping(path="/users/{id}")
	public ResponseEntity<Object> getUser(@PathVariable int id) {
		User user= userService.getUser(id);
		
		EntityModel<User> model=EntityModel.of(user);
		WebMvcLinkBuilder linkToUsers=linkTo(methodOn(this.getClass()).getAllUsers());
		model.add(linkToUsers
				.withRel(messageSource.getMessage("all-users-rel-link", null, LocaleContextHolder.getLocale())));
		//model.add(linkToUsers.withRel("all-users"));
		
		return new ResponseEntity<Object>(model,HttpStatus.OK);
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@DeleteMapping(path="/users")
	public ResponseEntity<String> deleteUsers() {
		return userService.deleteAllUsers();
	}
	
	@DeleteMapping(path="/users/{id}")
	public ResponseEntity<String> deleteUsers(@PathVariable int id) {
		return userService.deleteUser(id);
	}
	
}
