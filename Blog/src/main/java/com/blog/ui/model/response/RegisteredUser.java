package com.blog.ui.model.response;

import java.util.List;

import com.blog.io.entity.PostEntitiy;
import com.blog.shared.dto.PostDto;

public class RegisteredUser {
	private long userId;
	private String name;
	private String lastname;
	private String email;

	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
