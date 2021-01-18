package com.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.FriendCircle;
import com.spring.model.User;
import com.spring.repository.FriendCircleRepository;
import com.spring.service.FriendCircleService;

@RestController
public class FriendCircleController {
	@Autowired
	private FriendCircleService fcService;
	
	@RequestMapping(value = "/addfriendcircle", method = RequestMethod.PUT)
	public ResponseEntity addNewUser(@RequestBody FriendCircle f) {
		try {
			fcService.save(f);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/friendcircles")
	public ResponseEntity<List<FriendCircle>> getFriendCirclesByName(@RequestParam ("name") String name) {
		List<FriendCircle> friendCircles = fcService.findAll();
		List<FriendCircle> result = new ArrayList<FriendCircle>();
		for(FriendCircle fc : friendCircles) {
			if (fc.getName().contains(name)) {
				result.add(fc);
			}
		}
		if (result == null || result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="/friends/{no}", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getFriendsInCircle(@PathVariable("no") int friendCircleId) {
		List<User> friends = fcService.getFriendsInCircle(friendCircleId);
		if (friends==null || friends.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<>(friends, HttpStatus.OK);
	}
}
