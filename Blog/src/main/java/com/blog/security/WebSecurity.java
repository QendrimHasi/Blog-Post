package com.blog.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.blog.service.UserService;



@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		String[] permitted = new String[]{
                "/","/javatechie/**", "/png/**",
                "/css/**","/icons/**","/images/**","/src/main/webapp/uploads/images/**","/js/**"
        };
		
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.POST,SecurityConstants.SIGN_UP_URL)
		.permitAll()
		.antMatchers(permitted)
		.permitAll()
		.anyRequest()
		.authenticated().and()
		.addFilter(new AuthenticationFilter(authenticationManager()))
		.addFilter(new AuthorizationFilter(authenticationManager()));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

}
