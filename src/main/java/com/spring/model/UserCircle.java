package com.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_circle")
public class UserCircle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ucId;

	//@ManyToOne
    //@JoinColumn(name = "userId")
    private Integer user;
	
	//@ManyToOne
    //@JoinColumn(name = "friendCircleId")
    private Integer friendCircle;
	
	public UserCircle() {
		
	}

	public UserCircle(Integer user, Integer friendCircle) {
		super();
		this.user = user;
		this.friendCircle = friendCircle;
	}

	public Integer getUcId() {
		return ucId;
	}

	public void setUcId(Integer ucId) {
		this.ucId = ucId;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Integer getFriendCircle() {
		return friendCircle;
	}

	public void setFriendCircle(Integer friendCircle) {
		this.friendCircle = friendCircle;
	}
}
