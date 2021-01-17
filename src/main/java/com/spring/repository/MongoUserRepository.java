package com.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.model.MongoUser;

@Repository
public interface MongoUserRepository extends MongoRepository<MongoUser, String>{
	public MongoUser findByUsername(String username);
}
