package com.rest.restfulwebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.restfulwebservices.beans.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

}