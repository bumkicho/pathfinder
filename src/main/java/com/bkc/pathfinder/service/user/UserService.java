package com.bkc.pathfinder.service.user;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;

import com.bkc.pathfinder.model.authorization.PasswordEncoder;
import com.bkc.pathfinder.model.user.User;
import com.bkc.pathfinder.repository.user.UserRepository;

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
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User saveUser(User user) {
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		user.setCreatedDt(LocalDateTime.now());
		
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

}
