package com.blog.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blog.SpringApplicationContext;
import com.blog.service.UserService;
import com.blog.shared.dto.UserDto;
import com.blog.ui.model.request.UserLoginRequestModel;
import com.blog.ui.model.response.UserLoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserLoginRequestModel creds = new ObjectMapper()
					.readValue(request.getInputStream(), UserLoginRequestModel.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					creds.getEmail(),
					creds.getPassword(),
					new ArrayList<>()));
			
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String userName = ((User) authResult.getPrincipal()).getUsername();
		
		String token = Jwts.builder()
				.setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET)
				.compact();
		
		UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
		
		UserDto userDto= userService.getUser(userName);
		
	
		
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		UserLoginResponse user = new UserLoginResponse();
		
		BeanUtils.copyProperties(userDto, user);
		user.setToken(SecurityConstants.TOKEN_PREFIX + token);		
		String userout = new Gson().toJson(user);
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(userout);
		out.flush();
		
	}
	
}
