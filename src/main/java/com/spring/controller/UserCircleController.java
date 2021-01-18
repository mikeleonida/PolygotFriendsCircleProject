package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.FriendCircle;
import com.spring.service.UserCircleService;

@RestController
public class UserCircleController {
	@Autowired
	private UserCircleService userCircleService;
	
	@RequestMapping(value = "/addfriend/{user}/{circle}", method = RequestMethod.PUT)
	public ResponseEntity addFriendToCircle(@PathVariable ("user") int userId, 
											@PathVariable ("circle") int friendCircleId) {
		if(userCircleService.addFriend(userId, friendCircleId)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/removefriend/{user}/{circle}", method = RequestMethod.DELETE)
	public ResponseEntity removeFriendFromCircle(@PathVariable ("user") int userId, 
												@PathVariable ("circle") int friendCircleId) {
		if(userCircleService.removeFriend(userId, friendCircleId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping("/friendcircle/{no}")
	public ResponseEntity<List<FriendCircle>> getCircleByUserId(@PathVariable ("no") int userId) {
		//System.out.println("inside UserCircleController::getCircleByUserId");
		List<FriendCircle> fc = userCircleService.getFriendCircle(userId);
		if (fc != null && (!fc.isEmpty())) {
			return new ResponseEntity<>(fc, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
