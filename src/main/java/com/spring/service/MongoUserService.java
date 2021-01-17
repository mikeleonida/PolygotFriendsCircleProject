package com.spring.service;

public interface MongoUserService {
	public boolean login(String username, String password);
	public boolean logout(String username);
}
