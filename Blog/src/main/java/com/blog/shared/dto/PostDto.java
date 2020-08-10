package com.blog.shared.dto;

import java.util.List;

import com.blog.io.entity.CommentEntity;

public class PostDto {

	private long postId;
	private String title;
	private String description;
	private String img;
	private long userId;
	private List<CommentDto> coments;
	
	public List<CommentDto> getComents() {
		return coments;
	}

	public void setComents(List<CommentDto> coments) {
		this.coments = coments;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	private UserDto user;

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}





}
