package com.blog.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.blog.shared.dto.UserDto;

public interface UserService  extends UserDetailsService{
	UserDto createUser(UserDto user);
	UserDto getUserById(long id);
	UserDto updateUser(long id, UserDto user);
	void deleteUser(long id);
	UserDto getUser(String email);

}
