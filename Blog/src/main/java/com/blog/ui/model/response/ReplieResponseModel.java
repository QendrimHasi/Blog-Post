package com.blog.ui.model.response;

import java.util.List;

import com.blog.shared.dto.PostDto;

public class ReplieResponseModel {
	private long commentId;
	private String text;
	private RegisteredUser user;
	private PostResponseModel post;
	private SimpleCommentResponseModel comment;
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
	public SimpleCommentResponseModel getComment() {
		return comment;
	}
	public void setComment(SimpleCommentResponseModel comment) {
		this.comment = comment;
	}

	
	
}
