package com.rest.restfulwebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.restfulwebservices.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
