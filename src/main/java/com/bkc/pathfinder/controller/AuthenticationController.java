package com.bkc.pathfinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@PostMapping("signin") //api/authentication/signin
	public ResponseEntity<User> signIn(@RequestBody User user) {
		if (iUserService.findByUserName(user.getUserName())!=null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>(iUserService.saveUser(user), HttpStatus.CREATED);
	}
	
	@PostMapping("login") //api/authentication/login
	public ResponseEntity<User> logIn(@RequestBody User user) {
		ResponseEntity<User> responseUser = new ResponseEntity<>(iAuthenticationService.signInAndReturnJWT(user), HttpStatus.OK);
		return responseUser;
	}
	
}
