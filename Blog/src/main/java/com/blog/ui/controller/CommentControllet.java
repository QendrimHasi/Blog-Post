package com.blog.ui.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.service.CommentService;
import com.blog.shared.dto.CommentDto;
import com.blog.shared.dto.ReplieDto;
import com.blog.shared.dto.UserDto;
import com.blog.ui.model.request.CommentRequestModel;
import com.blog.ui.model.request.RepliesRequestModel;
import com.blog.ui.model.request.UpdateCommentRequestModel;
import com.blog.ui.model.response.CommentResponseModel;
import com.blog.ui.model.response.ErrorMessages;
import com.blog.ui.model.response.OperationStatusModel;
import com.blog.ui.model.response.ReplieResponseModel;

@RestController
@RequestMapping("comment") // http://localhost:8080/comments
public class CommentControllet {

	@Autowired
	CommentService service;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public CommentResponseModel createComment(@RequestBody CommentRequestModel comment) throws Exception {
		if (comment.getText().isEmpty())
			throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		ModelMapper mapper = new ModelMapper();
		CommentDto newComment = mapper.map(comment, CommentDto.class);

		CommentDto responseComment = service.createComment(newComment);


		CommentResponseModel returnValue = mapper.map(responseComment, CommentResponseModel.class);

		return returnValue;
	}

	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CommentResponseModel updateComment(@PathVariable long id, @RequestBody UpdateCommentRequestModel comment) throws Exception {
		if (comment.getText().isEmpty())
			throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		CommentDto newComment = new CommentDto();
		BeanUtils.copyProperties(comment, newComment);
		
		CommentDto responseComment = service.updateComment(id, newComment);
		
		ModelMapper mapper = new ModelMapper();
		CommentResponseModel returnvalue =mapper.map(responseComment, CommentResponseModel.class);

		return returnvalue;
	}

	@GetMapping(path = "/{Id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public CommentResponseModel getComnets(@PathVariable long Id) {

		CommentDto comment = service.getComment(Id);

		ModelMapper mapper = new ModelMapper();
		CommentResponseModel returnvalue = mapper.map(comment, CommentResponseModel.class);

		return returnvalue;
	}

	@DeleteMapping(path = "/{Id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteCommnet(@PathVariable long Id) {
		
		OperationStatusModel returnvalue = new OperationStatusModel();
		returnvalue.setOperationName("DELETE");
		
		service.deleteComment(Id);
		returnvalue.setOperationResult("SUCCES");
		return returnvalue;
	}

	
	@PostMapping(path = "/replie", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ReplieResponseModel CreateReplies(@RequestBody RepliesRequestModel replie)
			throws Exception {
		if (replie.getText().isEmpty() || replie.getText() == null)
			throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		ModelMapper mapper = new ModelMapper();

		ReplieDto newreplie = mapper.map(replie, ReplieDto.class);
		//newreplie.setIdOfComment(replie.getIdOfComment());
		ReplieDto savedreplie = service.createReplie(newreplie);

		ReplieResponseModel retunrvalue = mapper.map(savedreplie, ReplieResponseModel.class);

		return retunrvalue;
	}

	@PutMapping(path = "/replie/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ReplieResponseModel updateReplies(@PathVariable long id, @RequestBody UpdateCommentRequestModel replie) throws Exception {
		if(replie.getText().isEmpty() || replie.getText()==null) throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		ModelMapper mapper = new ModelMapper();
		
		CommentDto oldreplie = mapper.map(replie, CommentDto.class);
		
		CommentDto newreplie = service.updateReplie(id, oldreplie);
		
		ReplieResponseModel returnvalue = mapper.map(newreplie, ReplieResponseModel.class);
		
		return returnvalue;
	}

	@GetMapping(path = "/replie/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ReplieResponseModel getReplies(@PathVariable long id) {
		CommentDto replie= service.getReplie(id);
		ModelMapper mapper = new ModelMapper();
		ReplieResponseModel returnvalue = mapper.map(replie, ReplieResponseModel.class);
		return returnvalue;
	}

	@DeleteMapping(path = "/replie/{id}" , produces = {MediaType.APPLICATION_JSON_VALUE})
	public OperationStatusModel deleteReplies(@PathVariable long id) {
		OperationStatusModel returnvalue = new OperationStatusModel();
		returnvalue.setOperationName("DELETE");
		
		service.deleteComment(id);
		returnvalue.setOperationResult("SUCCES");
		return returnvalue;
	}

}
