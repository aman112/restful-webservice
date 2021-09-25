package com.rest.restfulwebservices.services;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.restfulwebservices.beans.User;
import com.rest.restfulwebservices.exceptions.UserNotFoundException;
import com.rest.restfulwebservices.repositories.UserDao;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	public User getUser(int id) {
		User user=userDao.getUser(id);
		if(user==null) {
			throw new UserNotFoundException("User doesn't exist");
		}
		return user;
	}
	
	public ResponseEntity<Object> saveUser(User u) {
		User savedUser=userDao.saveUser(u);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).body("User created suceesfully...");
	}
	
	public ResponseEntity<String> deleteAllUsers() {
		int deleted=userDao.deleteAllUsers();
		if(deleted==-1) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("All users already been deleted!!");
		}
		return ResponseEntity.ok("All users deleted!!");
	}
	
	public ResponseEntity<String> deleteUser(int id) {
		int deleted=userDao.deleteUser(id);
		if(deleted==-1) {
			throw new UserNotFoundException("User trying to delete doesn't exist!!");
		}
		return ResponseEntity.ok("User deleted!!");
	}
}
