package com.spring.service;

import java.util.List;

import com.spring.model.FriendCircle;

public interface UserCircleService {
	public boolean addFriend(int friendId, int friendCircleId);
	public boolean removeFriend(int friendId, int friendCircleId);
	public List<FriendCircle> getFriendCircle(int userId);
}
