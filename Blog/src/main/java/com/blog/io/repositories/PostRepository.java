package com.blog.io.repositories;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.blog.io.entity.PostEntitiy;
@Repository
public interface PostRepository extends PagingAndSortingRepository<PostEntitiy, Long> {
	PostEntitiy findById(long Id);
}
