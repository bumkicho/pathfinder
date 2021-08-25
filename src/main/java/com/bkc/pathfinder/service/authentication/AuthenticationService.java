package com.bkc.pathfinder.service.authentication;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.bkc.pathfinder.config.security.PFUserDetails;
import com.bkc.pathfinder.config.security.jwt.JwtProviderInterface;
import com.bkc.pathfinder.exception.PFException;
import com.bkc.pathfinder.model.user.User;
import com.bkc.pathfinder.model.user.VerificationToken;
import com.bkc.pathfinder.repository.user.UserRepository;
import com.bkc.pathfinder.repository.user.VerificationTokenRepository;
import com.bkc.pathfinder.service.user.UserServiceInterface;

@Service
public class AuthenticationService implements AuthenticationServiceInterface {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtProviderInterface iJwtProvider;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	VerificationTokenRepository tokenRepository;
	
	@Override
	public User signInAndReturnJWT(User requestUser) {
		
		Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(requestUser.getUserName(), requestUser.getUserPassword())
				);
		
		PFUserDetails userPrincipal = (PFUserDetails) auth.getPrincipal();
		User signedUser = userPrincipal.getUser();
		
		String jwt = iJwtProvider.generateToken(userPrincipal);
		
		signedUser.setToken(jwt);
		userRepository.save(signedUser);
		
		return signedUser;
	}

	@Override
	public void verify(String token) {
		Optional<VerificationToken> verificationToken = tokenRepository.findByToken(token);
		verificationToken.orElseThrow(() -> new PFException("Invalid Token"));
		
		User user = userRepository.findByUserName(verificationToken.get().getUser().getUserName());
		
		if(user!=null) {
			user.setActive(true);
			signInAndReturnJWT(user);			
		} else {
			throw new PFException("User not found");
		}
	}

}
