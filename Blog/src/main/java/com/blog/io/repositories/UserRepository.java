package com.blog.io.repositories;

import org.springframework.stereotype.Repository;

import com.blog.io.entity.UserEntity;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long>{
	UserEntity findByEmail(String email);
	UserEntity findById(long Id);
}
