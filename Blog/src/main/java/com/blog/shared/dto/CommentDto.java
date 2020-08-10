package com.blog.shared.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.blog.io.entity.CommentEntity;
import com.blog.io.entity.PostEntitiy;
import com.blog.io.entity.UserEntity;

public class CommentDto {
	
	private long commentId;
	private UserDto user;
	private PostDto post;
	private CommentDto comment;
	private List<CommentDto> replies;
	private String text;
	private long userId;
	private long postId;
	
	
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public PostDto getPost() {
		return post;
	}
	public void setPost(PostDto post) {
		this.post = post;
	}
	public CommentDto getComment() {
		return comment;
	}
	public void setComment(CommentDto comment) {
		this.comment = comment;
	}
	public List<CommentDto> getReplies() {
		return replies;
	}
	public void setReplies(List<CommentDto> replies) {
		this.replies = replies;
	}
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


	
	

}
