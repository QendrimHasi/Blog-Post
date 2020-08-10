package com.blog.shared.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.blog.io.entity.PostEntitiy;

public class UserDto {
	private long userId;
	private String name;
	private String lastname;
	private String email;
	private String password;
	private List<PostDto> posts;
	private List<CommentDto> coments;
	public List<PostDto> getPosts() {
		return posts;
	}
	public void setPosts(List<PostDto> posts) {
		this.posts = posts;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<CommentDto> getComents() {
		return coments;
	}
	public void setComents(List<CommentDto> coments) {
		this.coments = coments;
	}

	
	

}
