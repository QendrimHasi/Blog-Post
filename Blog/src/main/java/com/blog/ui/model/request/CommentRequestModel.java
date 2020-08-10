package com.blog.ui.model.request;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.blog.io.entity.CommentEntity;
import com.blog.io.entity.PostEntitiy;
import com.blog.io.entity.UserEntity;

public class CommentRequestModel {
	
	private String text;
	private long userId;
	private long postId;
	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	
	

}
