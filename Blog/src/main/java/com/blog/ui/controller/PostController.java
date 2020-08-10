package com.blog.ui.controller;

import java.nio.file.CopyOption;
import java.nio.file.StandardCopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.io.entity.PostEntitiy;
import com.blog.service.impl.PostServiceImpl;
import com.blog.shared.dto.PostDto;
import com.blog.ui.model.request.PostRequestModel;
import com.blog.ui.model.response.ErrorMessages;
import com.blog.ui.model.response.OperationStatusModel;
import com.blog.ui.model.response.PostProfileResponseModel;
import com.blog.ui.model.response.PostResponseModel;

@RestController
@RequestMapping("posts") // http://localhost:8080/posts
public class PostController {

	@Autowired
	PostServiceImpl postService;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public PostResponseModel savePost(@RequestBody PostRequestModel postModel) throws Exception {
		if (postModel.getTitle().isEmpty() || postModel.getDescription().isEmpty())
			throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		PostDto post = new PostDto();
		BeanUtils.copyProperties(postModel, post);

		PostDto newPost = postService.savePost(post);
		
		ModelMapper mapper = new ModelMapper();
		PostResponseModel returnvalue = mapper.map(newPost, PostResponseModel.class);

		return returnvalue;
	}

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public PostProfileResponseModel getPost(@PathVariable long id) {
		PostDto post = postService.getPostById(id);
		
		ModelMapper mapper = new ModelMapper();
		PostProfileResponseModel returnvalue = mapper.map(post, PostProfileResponseModel.class);

		return returnvalue;
	}

	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public PostResponseModel updatePost(@PathVariable long id, @RequestBody PostRequestModel postModel) throws Exception {

		if (postModel.getTitle().isEmpty() || postModel.getDescription().isEmpty())
			throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		PostDto post = new PostDto();
		BeanUtils.copyProperties(postModel, post);

		PostDto newPost = postService.updatePost(id, post);
		
		ModelMapper mapper = new ModelMapper();
		PostResponseModel returnvalue = mapper.map(newPost, PostResponseModel.class);

		return returnvalue;
	}

	@PutMapping("/upload/{id}")
	public PostResponseModel uploadFile(@PathVariable long id , @RequestParam("file") MultipartFile file) throws Exception {
		
		if(!file.getContentType().contains("image/")) throw new Exception(ErrorMessages.FORMAT_NOT_SUPORTED.getErrorMessage());
		
		PostDto newPost= postService.uploadFile(id,file);
		
		ModelMapper mapper = new ModelMapper();
		PostResponseModel returnvalue = mapper.map(newPost, PostResponseModel.class);

		return returnvalue;

	}

	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deletePost(@PathVariable long id) {
		OperationStatusModel returnvalue = new OperationStatusModel();
		returnvalue.setOperationName("DELETE");

		postService.deletePost(id);
		returnvalue.setOperationResult("SUCCES");

		return returnvalue;
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<PostResponseModel> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit) {

		if (page > 0)
			page = page - 1;
		
		List<PostDto> posts = postService.getPosts(page, limit);
		
		List<PostResponseModel> returnvalue = new ArrayList<>();
		ModelMapper mapper = new ModelMapper();
		for (PostDto postDto : posts) {
			PostResponseModel post = mapper.map(postDto, PostResponseModel.class);
			returnvalue.add(post);
		}
		
		return returnvalue;
	}

}
