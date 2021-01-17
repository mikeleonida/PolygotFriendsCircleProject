package com.spring.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.logger.LogFile;
import com.spring.model.MongoUser;
import com.spring.repository.MongoUserRepository;
import com.spring.service.MongoUserService;

@Service
public class MongoUserServiceImpl implements MongoUserService {

	@Autowired
	MongoUserRepository muRepository;
	
	@Override
	public boolean login(String username, String password) {
		try {
			MongoUser mu = muRepository.findByUsername(username);
			if (mu.getPassword().equals(password)) {
				//login succeeded, do logging
				LogFile.log("Successful login for user " + username);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//login failed, do logging
		LogFile.log("Login error for user " + username);
		return false;
	}

	@Override
	public boolean logout(String username) {
		try {
			MongoUser mu = muRepository.findByUsername(username);
			if (mu==null) {
				//logout failed, do logging
				LogFile.log("Logout failed no such user: " + username);
				return false;
			}
		} catch(Exception e) {
			//logout failed, do logging
			LogFile.log("Logout error for user " + username);
			return false;
		}
		//logout succeeded, do logging
		LogFile.log("Successful logout for user " + username);
		return true;
	}

}
