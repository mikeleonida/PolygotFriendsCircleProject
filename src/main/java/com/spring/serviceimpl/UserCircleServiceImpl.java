package com.spring.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.logger.LogFile;
import com.spring.model.FriendCircle;
import com.spring.model.User;
import com.spring.model.UserCircle;
import com.spring.repository.FriendCircleRepository;
import com.spring.repository.UserCircleRepository;
import com.spring.service.UserCircleService;

@Service
public class UserCircleServiceImpl implements UserCircleService {

	@Autowired
	UserCircleRepository ucRepository;
	
	@Autowired
	FriendCircleRepository fcRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
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
		List<FriendCircle> resultFcs = new ArrayList<FriendCircle>();
		try {
			String sqlString = "select f.friend_circle_id from friendcircle f inner join "
					+ "user_circle u on f.friend_circle_id=u.friend_circle where u.user=" + userId;
			Query query = entityManager.createNativeQuery(sqlString);
			List<Integer> ol = query.getResultList();
			List<FriendCircle> fcs = fcRepository.findAll();
			
			for (FriendCircle fc : fcs) {
				if (ol.contains(fc.getFriendCircleId())) {
					resultFcs.add(fc);
				}
			}
		} catch(Exception e) {
			LogFile.log(e.getMessage());
		}
		return resultFcs;
	}

}
