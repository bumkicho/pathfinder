package com.bkc.pathfinder.service.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.bkc.pathfinder.config.security.PFUserDetails;
import com.bkc.pathfinder.config.security.jwt.JwtProviderInterface;
import com.bkc.pathfinder.model.user.User;

@Service
public class AuthenticationService implements AuthenticationServiceInterface {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtProviderInterface iJwtProvider;
	
	@Override
	public User signInAndReturnJWT(User requestUser) {
		
		Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(requestUser.getUserName(), requestUser.getUserPassword())
				);
		
		PFUserDetails userPrincipal = (PFUserDetails) auth.getPrincipal();
		User signedUser = userPrincipal.getUser();
		
		String jwt = iJwtProvider.generateToken(userPrincipal);
		
		signedUser.setToken(jwt);
		
		return signedUser;
	}

}
