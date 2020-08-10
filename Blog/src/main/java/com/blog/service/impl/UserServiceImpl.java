package com.blog.service.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.blog.io.entity.UserEntity;
import com.blog.io.repositories.UserRepository;
import com.blog.service.UserService;
import com.blog.shared.dto.UserDto;
import com.blog.ui.model.response.ErrorMessages;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {
		UserEntity storedUserDetail = userRepository.findByEmail(user.getEmail());
		if (storedUserDetail != null) {
			throw new RuntimeException(ErrorMessages.RECORD_ALREDY_EXISTS.getErrorMessage());
		}

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		UserEntity savedUser = userRepository.save(userEntity);

		UserDto returnvalue = new UserDto();
		BeanUtils.copyProperties(savedUser, returnvalue);
		return returnvalue;
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		UserDto returnvalue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnvalue);
		return returnvalue;
	}

	@Override
	public UserDto getUserById(long id) {
		UserEntity user = userRepository.findById(id);

		if (user == null)
			throw new UsernameNotFoundException(id + "");

		ModelMapper model = new ModelMapper();
		UserDto returnvalue = model.map(user, UserDto.class);
		// BeanUtils.copyProperties(user, returnvalue);
		return returnvalue;
	}

	@Override
	public UserDto updateUser(long id, UserDto user) {
		UserDto returnvalue = new UserDto();

		UserEntity userEntity = userRepository.findById(id);
		if (userEntity == null)
			throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		if (userEntity.getEmail() != user.getEmail()) {
			UserEntity storedUserDetail = userRepository.findByEmail(user.getEmail());
			if (storedUserDetail != null) {
				throw new RuntimeException(ErrorMessages.EMAIL_EXIST.getErrorMessage());
			}
		}

		userEntity.setName(user.getName());
		userEntity.setLastname(user.getLastname());
		userEntity.setEmail(user.getEmail());
		userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		UserEntity updatedUser = userRepository.save(userEntity);

		BeanUtils.copyProperties(updatedUser, returnvalue);

		return returnvalue;
	}

	@Override
	public void deleteUser(long id) {
		UserEntity userEntity = userRepository.findById(id);
		if (userEntity == null)
			throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		userRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
	}

}
