package com.blog.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blog.shared.dto.PostDto;

public interface PostService {

	PostDto savePost(PostDto post);
	PostDto updatePost(long id, PostDto post);
	PostDto getPostById(long id);
	void deletePost(long id);
	List<PostDto> getPosts(int page, int limit);
	PostDto uploadFile(long id,MultipartFile file);
	void deleteFile(String id);
	
}
