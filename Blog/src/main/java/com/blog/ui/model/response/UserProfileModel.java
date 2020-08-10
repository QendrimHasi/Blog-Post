package com.blog.ui.model.response;

import java.util.List;

import com.blog.shared.dto.PostDto;

public class UserProfileModel {
	private long userId;
	private String name;
	private String lastname;
	private String email;
	private List<PostResponseModel> posts;
	private List<SimpleReplieModel> coments;

	
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
	public List<PostResponseModel> getPosts() {
		return posts;
	}
	public void setPosts(List<PostResponseModel> posts) {
		this.posts = posts;
	}
	public List<SimpleReplieModel> getComents() {
		return coments;
	}
	public void setComents(List<SimpleReplieModel> coments) {
		this.coments = coments;
	}




	
	
}
