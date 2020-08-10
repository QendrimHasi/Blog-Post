package com.blog.ui.model.response;

import java.util.List;

public class PostProfileResponseModel {
	
	private long postId;
	private String title;
	private String description;
	private String img;
	private RegisteredUser user;
	private List<CommentResponseModel> coments;
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
	public RegisteredUser getUser() {
		return user;
	}
	public void setUser(RegisteredUser user) {
		this.user = user;
	}
	public List<CommentResponseModel> getComents() {
		return coments;
	}
	public void setComents(List<CommentResponseModel> coments) {
		this.coments = coments;
	}
	
	

}
