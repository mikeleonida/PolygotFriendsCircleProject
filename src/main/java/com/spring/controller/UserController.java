package com.spring.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.User;
import com.spring.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping
@Api
public class UserController {
	@Autowired
	private UserRepository userService;
	
//	@RequestMapping(value="/", method = RequestMethod.GET)
//	public String welcome() {
//		return "redirect:/swagger-ui.html";
//	}
	
	@ApiOperation(value = "Add User")
	@RequestMapping(value = "/adduser", method = RequestMethod.PUT)
	public ResponseEntity addNewUser(@RequestBody User u) {
		try {
			return new ResponseEntity<>(userService.save(u), HttpStatus.CREATED);
		} catch(IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Get User By Number")
	@RequestMapping(value = "/user/{no}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserByNo(@PathVariable ("no")  int id ) {
		try {
			List<User> users = userService.findAll();
			for (User u : users) {
				if (u.getUserId().intValue()==id) {
					return new ResponseEntity<User>(u, HttpStatus.OK);
				}
			}
		} catch(EntityNotFoundException e) {
			
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Get All Users")
	@RequestMapping(value="/users", method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.findAll();
		if(users == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@ApiOperation(value = "Update User")
	@RequestMapping(value = "/update/{no}", method = RequestMethod.POST)
	public ResponseEntity updateUser(@RequestBody User u, @PathVariable("no") int id) {
		try {
			if (userService.existsById(id)) {
				u.setUserId(id);
				userService.save(u); 
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
