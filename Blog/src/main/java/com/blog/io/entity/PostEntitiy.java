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

@Entity(name = "posts")
public class PostEntitiy implements Serializable {

	private static final long serialVersionUID = 8421773948947732507L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long postId;
	@Column(nullable = false, length = 50)
	private String title;
	@Column(nullable = false, length = 256)
	private String description;
	@Column(length = 50)
	private String img;

	@ManyToOne
//	@Cascade(value={org.hibernate.annotations.CascadeType.ALL})
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "users_id")
	private UserEntity user;
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy = "post")
	private List<CommentEntity> coments;

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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<CommentEntity> getComents() {
		return coments;
	}

	public void setComents(List<CommentEntity> coments) {
		this.coments = coments;
	}

}
