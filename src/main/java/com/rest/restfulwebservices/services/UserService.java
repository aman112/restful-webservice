package com.rest.restfulwebservices.services;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
	
	@Autowired
	MessageSource messageSource;
	
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	public User getUser(int id) {
		User user=userDao.getUser(id);
		if(user==null) {
			throw new UserNotFoundException(
					messageSource.getMessage("user-notfound-exception-get", null, LocaleContextHolder.getLocale()));
			//throw new UserNotFoundException("User doesn't exist");
		}
		return user;
	}
	
	public ResponseEntity<Object> saveUser(User u) {
		User savedUser=userDao.saveUser(u);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).body(
				messageSource.getMessage("user-created-success",null, LocaleContextHolder.getLocale()));
		//return ResponseEntity.created(location).body("User created suceesfully...");
	}
	
	public ResponseEntity<String> deleteAllUsers() {
		int deleted=userDao.deleteAllUsers();
		if(deleted==-1) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(messageSource.getMessage("user-notfound-exception-delete", null, LocaleContextHolder.getLocale()));
		}
		return ResponseEntity.ok(messageSource.getMessage("all-users-deleted", null, LocaleContextHolder.getLocale()));
	}
	
	public ResponseEntity<String> deleteUser(int id) {
		int deleted=userDao.deleteUser(id);
		if(deleted==-1) {
			throw new UserNotFoundException(
					messageSource.getMessage("user-notfound-exception-delete", null, LocaleContextHolder.getLocale()));
		}
		return ResponseEntity.ok(messageSource.getMessage("user-deleted-success", null, LocaleContextHolder.getLocale()));
	}
}
