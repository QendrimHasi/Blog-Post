package com.blog.ui.model.response;

public class SimpleReplieModel {
	
	private long commentId;
	private String text;
	private RegisteredUser user;
	private PostResponseModel post;
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public RegisteredUser getUser() {
		return user;
	}
	public void setUser(RegisteredUser user) {
		this.user = user;
	}
	public PostResponseModel getPost() {
		return post;
	}
	public void setPost(PostResponseModel post) {
		this.post = post;
	}
	
	

}
