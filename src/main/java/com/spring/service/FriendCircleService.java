package com.spring.service;

import java.util.List;

import com.spring.model.FriendCircle;
import com.spring.model.User;

public interface FriendCircleService {
	public FriendCircle save(FriendCircle fc);
	public List<FriendCircle> findAll();
	public List<User> getFriendsInCircle(int fcId);
}
