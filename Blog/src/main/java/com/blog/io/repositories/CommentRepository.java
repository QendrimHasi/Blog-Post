package com.blog.io.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blog.io.entity.CommentEntity;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Long> {

	CommentEntity findById(long Id);
}
