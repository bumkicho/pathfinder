package com.bkc.pathfinder.service.user;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;

import com.bkc.pathfinder.config.security.PFPasswordEncoder;
import com.bkc.pathfinder.model.user.Role;
import com.bkc.pathfinder.model.user.User;
import com.bkc.pathfinder.model.user.UserRole;
import com.bkc.pathfinder.repository.user.RoleRepository;
import com.bkc.pathfinder.repository.user.UserRepository;
import com.bkc.pathfinder.repository.user.UserRoleRepository;

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
	
	/*
	 * SAVE OPERATIONS
	 */
	@Override
	public User saveUser(User user) {
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		user.setCreatedDt(LocalDateTime.now());
		user.setActive(true);
		
		return userRepository.save(user);
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

}
