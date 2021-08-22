package com.bkc.pathfinder.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bkc.pathfinder.model.user.Role;
import com.bkc.pathfinder.model.user.User;
import com.bkc.pathfinder.model.user.UserRole;

/**
 * 
 * @author bumki
 *
 */


@Service
public interface UserServiceInterface {

	User saveUser(User user);
	Role saveRole(Role role);

	User findByUserName(String userName);
	UserRole saveUserRole(User user, Role role);
	List<Role> getRolesByUser(User user);

}
