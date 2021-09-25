package com.rest.restfulwebservices.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.restfulwebservices.beans.User;

@Component
public class UserDao {
	
	private static List<User> users=new ArrayList<>();
	private static long usersCount=2;
	
	static {
		users.add(new User(1L,"A",25,LocalDate.of(2000, 1, 21)));
		users.add(new User(2L,"B",21,LocalDate.of(2001, 2, 12)));
	}
	
	//retrieve all
	public List<User> getAllUsers(){
		return users;
	}
	//retrieve user
	public User getUser(int id) {
		User u=null;
		for(User u1:users) {
			if(u1.getId()==id) {
				u=u1;
				break;
			}
		}
		return u;
	}
	//save user
	public User saveUser(User u) {
		if(u.getId()==null) {
			u.setId(++usersCount);
		}
		users.add(u);
		return u;
	}
	
	//delete All users
	public int deleteAllUsers() {
		int deleted=-1;
		if(users.size()>0) {
			users.clear();
			usersCount=0;
			
			deleted=0;
		}
		return deleted;
	}
	
	//delete user
	public int deleteUser(int id) {
		int deleted=-1;
		Iterator<User> itr=users.iterator();
		while(itr.hasNext()) {
			User u=itr.next();
			if(u.getId()==id) {
				itr.remove();
				deleted=0;
				break;
			}
		}
		return deleted;
	}
}
