package com.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friendcircle")
public class FriendCircle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer friendCircleId;

	@Column(nullable = false)
	private String name;
	
	public FriendCircle() {
		
	}

	public FriendCircle(String name) {
		super();
		this.name = name;
	}

	public Integer getFriendCircleId() {
		return friendCircleId;
	}

	public void setFriendCircleId(Integer friendCircleId) {
		this.friendCircleId = friendCircleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
