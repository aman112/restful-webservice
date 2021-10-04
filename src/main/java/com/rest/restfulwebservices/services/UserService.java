package com.rest.restfulwebservices.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.restfulwebservices.beans.Post;
import com.rest.restfulwebservices.beans.User;
import com.rest.restfulwebservices.exceptions.UserNotFoundException;
import com.rest.restfulwebservices.repositories.PostRepository;
//import com.rest.restfulwebservices.repositories.UserDao;
import com.rest.restfulwebservices.repositories.UserRepository;

@Service
public class UserService {

	/*@Autowired
	UserDao userDao;*/
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	MessageSource messageSource;
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User getUser(long id) {
		Optional<User> userOp=userRepository.findById(id);
		if(!userOp.isPresent()) {
			throw new UserNotFoundException(
					messageSource.getMessage("user-notfound-exception-get", null, LocaleContextHolder.getLocale()));
			//throw new UserNotFoundException("User doesn't exist");
		}
		return userOp.get();
	}
	
	public ResponseEntity<Object> saveUser(User u) {
		User savedUser=userRepository.save(u);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).body(
				messageSource.getMessage("user-created-success",null, LocaleContextHolder.getLocale()));
		//return ResponseEntity.created(location).body("User created successfully...");
	}
	
	public ResponseEntity<String> deleteAllUsers() {
		if(userRepository.count()>0) {
			userRepository.deleteAll();

			return ResponseEntity.ok(messageSource.getMessage("all-users-deleted", null, LocaleContextHolder.getLocale()));
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(messageSource.getMessage("all-users-deleted-notfound", null, LocaleContextHolder.getLocale()));
		}
	}
	
	public ResponseEntity<String> deleteUser(long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
			return ResponseEntity.ok(messageSource.getMessage("user-deleted-success", null, LocaleContextHolder.getLocale()));
		}
		else {
			throw new UserNotFoundException(
				messageSource.getMessage("user-notfound-exception-delete", null, LocaleContextHolder.getLocale()));	
		}
	}
	
	public List<Post> getAllPostsByUser(Long userId) {
		User user = getUser(userId);
		return user.getPosts();
	}
	public void getPostByUser(Long userId, Long postId) {
		
	}
	
	public void savePostsByUser(Long userId,Post post) {
		User user=getUser(userId);
		post.setUser(user);
		
		Post newPost=postRepository.save(post);
		System.out.println("Post saved::"+newPost.toString());
	}
	
	public void deleteAllPostsByUser(Long userId) {
		
	}
	
	public void deletePostByUser(Long userId, Long postId) {
		
	}
}
