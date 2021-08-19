package com.bkc.pathfinder.service.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bkc.pathfinder.model.user.User;

/**
 * 
 * @author bumki
 *
 */


@Service
public interface UserServiceInterface {

	User saveUser(User user);

	Optional<User> findByUserName(String userName);

}
