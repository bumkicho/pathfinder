package com.bkc.pathfinder.service.authentication;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.bkc.pathfinder.config.security.PFPasswordEncoder;
import com.bkc.pathfinder.config.security.PFUserDetails;
import com.bkc.pathfinder.config.security.jwt.JwtProviderInterface;
import com.bkc.pathfinder.exception.PFException;
import com.bkc.pathfinder.model.user.Role;
import com.bkc.pathfinder.model.user.User;
import com.bkc.pathfinder.model.user.UserRole;
import com.bkc.pathfinder.model.user.VerificationToken;
import com.bkc.pathfinder.repository.user.RoleRepository;
import com.bkc.pathfinder.repository.user.UserRepository;
import com.bkc.pathfinder.repository.user.UserRoleRepository;
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
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	VerificationTokenRepository tokenRepository;
	
	@Autowired
	private PFPasswordEncoder passwordEncoder;

	@Override
	public User signInAndReturnJWT(User requestUser) {
		
		String presentedUserPassword = passwordEncoder.matches(PFPasswordEncoder.DEFAULT_PASSWORD, requestUser.getUserPassword())?PFPasswordEncoder.DEFAULT_PASSWORD:requestUser.getUserPassword();

		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(requestUser.getUserName(), presentedUserPassword));

		PFUserDetails userPrincipal = (PFUserDetails) auth.getPrincipal();
		User signedUser = userPrincipal.getUser();

		String jwt = iJwtProvider.generateToken(userPrincipal);

		signedUser.setToken(jwt);
		userRepository.save(signedUser);

		return signedUser;
	}

	@Override
	public String verify(String token) {
		Optional<VerificationToken> verificationToken = tokenRepository.findByToken(token);
		verificationToken.orElseThrow(() -> new PFException("Invalid Token"));

		User user = userRepository.findByUserName(verificationToken.get().getUser().getUserName());

		if (user != null) {
			if (userRoleRepository.findAllByUser(user).size() == 0) {
				Role role = roleRepository.findRoleByRoleName("USER");
				UserRole userRole = new UserRole();
				userRole.setUser(user);
				userRole.setRole(role);
				userRole.setCreatedDt(LocalDateTime.now());
				userRoleRepository.save(userRole);
			}
			user.setActive(true);
			return signInAndReturnJWT(user).getToken();
		} else {
			throw new PFException("User not found");
		}
	}

}
