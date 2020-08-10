package com.blog.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.io.entity.PostEntitiy;
import com.blog.io.entity.UserEntity;
import com.blog.io.repositories.PostRepository;
import com.blog.io.repositories.UserRepository;
import com.blog.service.PostService;
import com.blog.shared.dto.PostDto;
import com.blog.ui.model.response.ErrorMessages;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
@Service
public class PostServiceImpl implements PostService {
	

	
	@Autowired
	PostRepository repository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public PostDto savePost(PostDto post) {
		
		UserEntity user = userRepository.findById(post.getUserId());
		
		if(user == null) throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		PostEntitiy newPost = new PostEntitiy();
		
		BeanUtils.copyProperties(post, newPost);
		newPost.setUser(user);
		
		PostEntitiy savedPost = repository.save(newPost);
		
		ModelMapper mapper = new ModelMapper();
		PostDto returnValue = mapper.map(savedPost, PostDto.class);
		
		
		return returnValue;
	}

	@Override
	public PostDto updatePost(long id, PostDto post) {
		
		PostEntitiy postDetail = repository.findById(id);
		if(postDetail == null) throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		postDetail.setTitle(post.getTitle());
		postDetail.setDescription(post.getDescription());
		
		PostEntitiy updatedPost = repository.save(postDetail);
		
		ModelMapper mapper = new ModelMapper();
		PostDto returnValue = mapper.map(updatedPost, PostDto.class);
		
		return returnValue;
	}

	@Override
	public PostDto getPostById(long id) {
		PostEntitiy post = repository.findById(id);
		
		if(post == null) throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		ModelMapper mapper = new ModelMapper();
		PostDto returnValue = mapper.map(post, PostDto.class);
		
		return returnValue;
	}

	@Override
	public void deletePost(long id) {
		PostEntitiy post = repository.findById(id);		
		if(post == null) throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		deleteFile(post.getImg());
		repository.deleteById(id);
		
	}

	@Override
	public List<PostDto> getPosts(int page, int limit) {
		List<PostDto> returnvalue = new ArrayList<>();
		Pageable pageable = PageRequest.of(page, limit,Sort.Direction.DESC,"postId");
		
		Page<PostEntitiy> postsPage = repository.findAll(pageable);
		List<PostEntitiy> posts = postsPage.getContent();
		ModelMapper mapper = new ModelMapper();
		for (PostEntitiy postEntity : posts) {
			PostDto postDto = mapper.map(postEntity, PostDto.class);
			returnvalue.add(postDto);
		}
		return returnvalue;
	}

	@Override
	public PostDto uploadFile(long id , MultipartFile file) {
		String directory="./src/main/resources/static/images/";
		String type = file.getContentType().replace("image/","");
		String encryptedFileName = UUID.randomUUID()+"."+type;
		String destinationFile = directory + encryptedFileName;
		
		PostEntitiy post = repository.findById(id);	
		
		if(post.getImg()!=null) {
			Path p= Paths.get(directory+post.getImg());
			try {
				Files.delete(p);
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
			
		}
		
		
		try {
			Files.copy(file.getInputStream(), Paths.get(destinationFile), StandardCopyOption.REPLACE_EXISTING);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		post.setImg(encryptedFileName);
		PostEntitiy updatedpost = repository.save(post); 
		
		ModelMapper mapper = new ModelMapper();
		PostDto returnValue = mapper.map(updatedpost, PostDto.class);
		return returnValue;
	}

	@Override
	public void deleteFile(String id) {
		if(id!=null) {
			String directory="./src/main/resources/static/images/";
			Path p= Paths.get(directory+ id);
			try {
				Files.delete(p);
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}	
		}
		
	}
	
	

}
