package com.blog.io.entity;

import java.io.Serializable;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name = "comments")
public class CommentEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long commentId;
	
	@Column(nullable = false, length = 256)
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	private PostEntitiy post;
	
	@ManyToOne
	@JoinColumn(name = "comment_id")
	private CommentEntity comment;
	
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy = "comment")
	private List<CommentEntity> replies;


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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public PostEntitiy getPost() {
		return post;
	}

	public void setPost(PostEntitiy post) {
		this.post = post;
	}

	public CommentEntity getComment() {
		return comment;
	}

	public void setComment(CommentEntity comment) {
		this.comment = comment;
	}

	public List<CommentEntity> getReplies() {
		return replies;
	}

	public void setReplies(List<CommentEntity> replies) {
		this.replies = replies;
	}



	
	


}
