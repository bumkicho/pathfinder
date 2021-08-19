package com.bkc.pathfinder.service.user;

import java.util.Optional;

import com.bkc.pathfinder.model.user.User;

/**
 * 
 * @author bumki
 *
 */
public interface UserServiceInterface {

	User saveUser(User user);

	Optional<User> findByUserName(String userName);

}
