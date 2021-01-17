package com.spring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="UserFriendCircle")
public class MongoUser {
	@Id
	private String id; //Default @Id String in MongoDB

	//@NotBlank
	@Field("username")
	private String username;
	
	//@NotBlank
	@Field("password")
	private String password;
	
	public MongoUser() {
		
	}

	public MongoUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
