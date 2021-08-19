package com.bkc.pathfinder.service.user;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;

import com.bkc.pathfinder.model.authorization.PasswordEncoder;
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
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User saveUser(User user) {
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		user.setCreatedDt(LocalDateTime.now());
		
		return userRepository.save(user);
	}
	
	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Optional<User> findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public UserRole saveUserRole(User user, Role role) {
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		userRole.setCreatedDt(LocalDateTime.now());
		return userRoleRepository.save(userRole);		
	}

}
