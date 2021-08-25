package com.bkc.pathfinder.service.authentication;

import org.springframework.stereotype.Service;

import com.bkc.pathfinder.model.user.User;

@Service
public interface AuthenticationServiceInterface {

	User signInAndReturnJWT(User requestUser);

	void verify(String token);

}
