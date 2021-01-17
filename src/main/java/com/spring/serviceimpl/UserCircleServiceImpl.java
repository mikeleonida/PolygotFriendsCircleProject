package com.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.model.FriendCircle;
import com.spring.model.User;
import com.spring.model.UserCircle;
import com.spring.repository.UserCircleRepository;
import com.spring.service.UserCircleService;

@Service
public class UserCircleServiceImpl implements UserCircleService {

	@Autowired
	UserCircleRepository ucRepository;
	
	@Override
	public boolean addFriend(int friendId, int friendCircleId) {
		try {
			List<UserCircle> ucs = ucRepository.findAll();
			for (UserCircle uEntry : ucs) {
				if (uEntry.getUser().intValue() == friendId &&
						uEntry.getFriendCircle().intValue() == friendCircleId) {
					//entry already exists
					return false;
				}
			}
			
			ucRepository.save(new UserCircle(friendId, friendCircleId));
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeFriend(int friendId, int friendCircleId) {
		boolean foundEntry = false;
		try {
			List<UserCircle> ucs = ucRepository.findAll();
			for (UserCircle uEntry : ucs) {
				if (uEntry.getUser().intValue() == friendId &&
						uEntry.getFriendCircle().intValue() == friendCircleId) {
					foundEntry = true;
					ucRepository.delete(uEntry);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return foundEntry;
	}

	@Override
	public List<FriendCircle> getFriendCircle(int userId) {
		try {
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
