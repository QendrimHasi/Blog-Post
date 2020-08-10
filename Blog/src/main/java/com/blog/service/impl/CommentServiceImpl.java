package com.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.blog.io.entity.CommentEntity;
import com.blog.io.entity.PostEntitiy;
import com.blog.io.entity.UserEntity;
import com.blog.io.repositories.CommentRepository;
import com.blog.io.repositories.PostRepository;
import com.blog.io.repositories.UserRepository;
import com.blog.service.CommentService;
import com.blog.shared.dto.CommentDto;
import com.blog.shared.dto.PostDto;
import com.blog.shared.dto.ReplieDto;
import com.blog.shared.dto.UserDto;
import com.blog.ui.model.response.ErrorMessages;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentrepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PostRepository PostRepository;

	@Override
	public CommentDto createComment(CommentDto comment) {
		
		ModelMapper mapper = new ModelMapper();
		CommentEntity newCommentEntity = mapper.map(comment, CommentEntity.class);

		UserEntity user = userRepository.findById(comment.getUserId());
		if (user == null)
			throw new RuntimeException(ErrorMessages.USER_NOT_FOUND.getErrorMessage());
		PostEntitiy post = PostRepository.findById(comment.getPostId());
		if (post == null)
			throw new RuntimeException(ErrorMessages.POST_NOT_FOUND.getErrorMessage());

		newCommentEntity.setUser(user);
		newCommentEntity.setPost(post);

		CommentEntity savedComment = commentrepository.save(newCommentEntity);
		
		CommentDto retunrValue = mapper.map(savedComment, CommentDto.class);

		return retunrValue;
	}

	@Override
	public CommentDto updateComment(long id, CommentDto comment) {
		CommentEntity oldcomment= commentrepository.findById(id);
		oldcomment.setText(comment.getText());
		
		CommentEntity newComment = commentrepository.save(oldcomment);
		
		ModelMapper mapper = new ModelMapper();
		CommentDto returnvalue = mapper.map(newComment, CommentDto.class);
		return returnvalue;
	}

	@Override
	public CommentDto getComment(long Id) {

		CommentEntity comment = commentrepository.findById(Id);

		ModelMapper mapper = new ModelMapper();
		CommentDto returnvalue = mapper.map(comment, CommentDto.class);

		return returnvalue;
	}
	@Override
	public void deleteComment(long id) {
		CommentEntity comment = commentrepository.findById(id);
		if(comment == null) throw new RuntimeException(ErrorMessages.REPLIE_NOT_FOUND.getErrorMessage());
		
		commentrepository.deleteById(id);
	}

	@Override
	public ReplieDto createReplie(ReplieDto replie) {
		UserEntity user = userRepository.findById(replie.getUserId());
		if (user == null)
			throw new RuntimeException(ErrorMessages.USER_NOT_FOUND.getErrorMessage());
		CommentEntity comment = commentrepository.findById(replie.getCommentId());
		if (comment == null)
			throw new RuntimeException(ErrorMessages.COMMENT_NOT_FOUND.getErrorMessage());
		PostEntitiy post = PostRepository.findById(comment.getPost().getPostId());
		if (post == null)
			throw new RuntimeException(ErrorMessages.POST_NOT_FOUND.getErrorMessage());

		ModelMapper mapper = new ModelMapper();

		CommentEntity newreplie = mapper.map(replie, CommentEntity.class);
		newreplie.setCommentId(replie.getReplieId());
		newreplie.setUser(user);
		newreplie.setComment(comment);
		newreplie.setPost(post);

		CommentEntity savedReplie = commentrepository.save(newreplie);
		ReplieDto retunrValue = mapper.map(savedReplie, ReplieDto.class);
		retunrValue.setComment(mapper.map(savedReplie.getComment(), CommentDto.class));

		return retunrValue;
	}

	@Override
	public CommentDto updateReplie(long id, CommentDto replie) {
		CommentEntity oldreplie = commentrepository.findById(id);
		if (oldreplie==null) throw new RuntimeException(ErrorMessages.REPLIE_NOT_FOUND.getErrorMessage());
		oldreplie.setText(replie.getText());
		CommentEntity newreplie = commentrepository.save(oldreplie);
		
		ModelMapper mapper = new ModelMapper();
		CommentDto returnvalue = mapper.map(newreplie, CommentDto.class);
		
		return returnvalue;
	}

	@Override
	public CommentDto getReplie(long id) {
		CommentEntity replie = commentrepository.findById(id);
		if(replie==null) throw new RuntimeException(ErrorMessages.REPLIE_NOT_FOUND.getErrorMessage());
		
		ModelMapper mapper = new ModelMapper();
		CommentDto returnvalue = mapper.map(replie, CommentDto.class);
		return returnvalue;
	}

	@Override
	public void deleteReplie(long id) {
		CommentEntity replie = commentrepository.findById(id);
		if(replie == null) throw new RuntimeException(ErrorMessages.REPLIE_NOT_FOUND.getErrorMessage());
		
		commentrepository.deleteById(id);
	}


}
