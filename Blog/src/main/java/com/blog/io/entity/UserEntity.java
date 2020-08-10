package com.blog.io.entity;

import java.io.Serializable;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;




@Entity(name = "users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 3140671550013079103L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long userId;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 50)
	private String lastname;

	@Column(nullable = false, length = 120, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;
	
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy = "user")
	private List<PostEntitiy> posts;
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy = "user")
	private List<CommentEntity> coments;

	public List<PostEntitiy> getPosts() {
		return posts;
	}

	public void setPosts(List<PostEntitiy> posts) {
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

	public List<CommentEntity> getComents() {
		return coments;
	}

	public void setComents(List<CommentEntity> coments) {
		this.coments = coments;
	}

}
