package com.spring.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.logger.LogFile;
import com.spring.model.FriendCircle;
import com.spring.model.User;
import com.spring.repository.FriendCircleRepository;
import com.spring.repository.UserRepository;
import com.spring.service.FriendCircleService;

@Service
public class FriendCircleServiceImpl implements FriendCircleService {

	@Autowired
	private FriendCircleRepository fcRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public FriendCircle save(FriendCircle fc) {
		return fcRepository.save(fc);
	}

	@Override
	public List<FriendCircle> findAll() {
		return fcRepository.findAll();
	}

	@Override
	public List<User> getFriendsInCircle(int fcId) {
		List<User> resultList = new ArrayList<User>();
		try {
//			String sqlString = "select u.user_id from user u inner join user_circle uc on "
//					+ "u.user_id=uc.user where uc.friend_circle=" + fcId;
			String sqlString = "select user from user_circle where friend_circle=" + fcId;
			Query query = entityManager.createNativeQuery(sqlString);
			List<Integer> ol = query.getResultList();
			List<User> users = userRepository.findAll();
			
			for (User u : users) {
				if (ol.contains(u.getUserId())) {
					resultList.add(u);
				}
			}
		} catch(Exception e) {
			LogFile.log(e.getMessage());
		}
		return resultList;
	}
	
	
	
}
