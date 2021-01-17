package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.service.MongoUserService;

@RestController
public class UserAuthController {
	@Autowired
	private MongoUserService userService;	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity loginUser(@RequestParam ("username") String username,
			@RequestParam ("password") String password) {
		if(userService.login(username, password)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity logoutUser(@RequestParam ("username") String username) {
		if(userService.logout(username)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}
