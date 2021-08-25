package com.bkc.pathfinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkc.pathfinder.model.user.User;
import com.bkc.pathfinder.service.authentication.AuthenticationServiceInterface;
import com.bkc.pathfinder.service.user.UserServiceInterface;

/**
 * 
 * @author bumki
 *
 */

/*
 * AuthenticationController to sign
 */

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationServiceInterface iAuthenticationService;
	
	@Autowired
	private UserServiceInterface iUserService;
	
	//ResponseEntity - response status, response header, response body
	@PostMapping("register") //api/authentication/register
	public ResponseEntity<User> register(@RequestBody User user) {
		User existingUser = iUserService.findByUserName(user.getUserName());
		if (existingUser!=null) {
			return new ResponseEntity<>(existingUser, HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>(iUserService.registerUser(user), HttpStatus.CREATED);
	}
	
	@PostMapping("verify/{token}")
	public ResponseEntity<String> verify(@PathVariable String token) {
		iAuthenticationService.verify(token);
		
		return new ResponseEntity<>("verified", HttpStatus.OK);
	}
	
	@PostMapping("login") //api/authentication/login
	public ResponseEntity<User> logIn(@RequestBody User user) {
		ResponseEntity<User> responseUser = new ResponseEntity<>(iAuthenticationService.signInAndReturnJWT(user), HttpStatus.OK);
		return responseUser;
	}
	
}
