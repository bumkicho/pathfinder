package com.bkc.pathfinder.service.user;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.*;

import com.bkc.pathfinder.config.security.PFPasswordEncoder;
import com.bkc.pathfinder.model.user.Role;
import com.bkc.pathfinder.model.user.User;
import com.bkc.pathfinder.model.user.UserRole;
import com.bkc.pathfinder.model.user.VerificationToken;
import com.bkc.pathfinder.reddit.model.NotificationEmail;
import com.bkc.pathfinder.reddit.service.MailService;
import com.bkc.pathfinder.repository.user.RoleRepository;
import com.bkc.pathfinder.repository.user.UserRepository;
import com.bkc.pathfinder.repository.user.UserRoleRepository;
import com.bkc.pathfinder.repository.user.VerificationTokenRepository;

/**
 * 
 * @author bumki
 *
 */

@Service
public class UserService implements UserServiceInterface {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private PFPasswordEncoder passwordEncoder;
	
	@Autowired
	private VerificationTokenRepository tokenRepository;
	
	@Autowired
	private MailService mailService;
	
	/*
	 * SAVE OPERATIONS
	 */
	@Override
	public User registerUser(User user) {
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		user.setCreatedDt(LocalDateTime.now());
		user.setActive(false);
		
		return generateVerificationToken(userRepository.save(user));
	}
	
	private User generateVerificationToken(User user) {

		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		
		tokenRepository.save(verificationToken);
		
		mailService.sendMailViaMailGun(new NotificationEmail("Please Activate your account",
				user.getUserEmail(),
				"Thank you for signing up to Pathfinder, " +
				"Please click on the below url to activate your account: " +
				"http://bkcpathfinder.herokuapp.com/api/authentication/verify/" +
				token));
		
		return user;
		
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public UserRole saveUserRole(User user, Role role) {
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		userRole.setCreatedDt(LocalDateTime.now());
		return userRoleRepository.save(userRole);		
	}

	/*
	 * FIND USER
	 */
	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	
	@Override
	public User findByUserIdAndPassword(String userId, String password) {
		return userRepository.findByUserIdAndPassword(userId, password);
	}
	
	/*
	 * FIND ROLE
	 */
	@Override
	public List<Role> getRolesByUser(User user) {
		List<UserRole> userRoles = userRoleRepository.findAllByUser(user);
		List<Role> roles = new ArrayList<Role>();
		//userRoles.stream().forEach(ur -> roles.add(ur.getRole()));
		if(!userRoles.isEmpty()) {
			for(UserRole userRole : userRoles) {
				roles.add(userRole.getRole());
			}
		}
		
		return roles;
	}
	
	@Override
	public Role findRoleByRoleName(String roleName) {
		Role role = roleRepository.findRoleByRoleName(roleName);
		return role;
	}
	
	@Override
	public List<Role> setRolesForUser(User user, Role role) {
		List<Role> roles = getRolesByUser(user);
		if(!roles.contains(role)) {
			try {
				saveUserRole(user, role);
				roles.add(role);
			} catch (Exception e) {
				;
			}
		}
		return roles;
	}

}
