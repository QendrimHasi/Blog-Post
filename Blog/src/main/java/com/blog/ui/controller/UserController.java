package com.blog.ui.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.service.UserService;
import com.blog.shared.dto.UserDto;
import com.blog.ui.model.request.UserRequestModel;
import com.blog.ui.model.response.ErrorMessages;
import com.blog.ui.model.response.OperationStatusModel;
import com.blog.ui.model.response.RegisteredUser;
import com.blog.ui.model.response.UserProfileModel;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}",	produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserProfileModel getUsers(@PathVariable long id) {
		UserDto user = userService.getUserById(id);
		
		//BeanUtils.copyProperties(user, returnvalue);
		ModelMapper model = new ModelMapper();
		UserProfileModel returnvalue = model.map(user, UserProfileModel.class);
		
		return returnvalue;
	}

	@PostMapping(
			consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public RegisteredUser registerUsers(@RequestBody UserRequestModel usermodel) throws Exception {
		
		if(usermodel.getName().isEmpty() ||
				usermodel.getLastname().isEmpty()||
				usermodel.getEmail().isEmpty()||
				usermodel.getPassword().isEmpty()) throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		UserDto user = new UserDto();
		BeanUtils.copyProperties(usermodel, user);
		
		UserDto createdUser = userService.createUser(user);
		
		RegisteredUser userres= new RegisteredUser();
		BeanUtils.copyProperties(createdUser, userres);
		return userres;
	}

	@PutMapping(path = "/{id}",
			consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public RegisteredUser updateUser(@PathVariable long id, @RequestBody UserRequestModel usermodel)throws Exception {
		if(usermodel.getName().isEmpty() ||
				usermodel.getLastname().isEmpty()||
				usermodel.getEmail().isEmpty()||
				usermodel.getPassword().isEmpty()) throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		UserDto user = new UserDto();
		BeanUtils.copyProperties(usermodel, user);
		
		UserDto updatedUser = userService.updateUser(id, user);
		RegisteredUser returnvalue = new RegisteredUser();
		
		BeanUtils.copyProperties(updatedUser, returnvalue);
		
		return returnvalue;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deletedUser(@PathVariable long id) {
		OperationStatusModel returnvalue = new OperationStatusModel();
		returnvalue.setOperationName("DELETE");
		
		userService.deleteUser(id);
		returnvalue.setOperationResult("SUCCES");
		
		return returnvalue;
	}

}
