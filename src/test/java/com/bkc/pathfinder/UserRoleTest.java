package com.bkc.pathfinder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bkc.pathfinder.model.user.*;
import com.bkc.pathfinder.service.user.UserService;

/**
 * 
 * @author bumki
 *
 */

@SpringBootTest
public class UserRoleTest {
	
	@Autowired
	private User user;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Role role;
	
	@Autowired
	private UserRole userRole;
	
	@Test
	public void save_user_test() {
		user = new User();
		user.setUserId("bcho");
		user.setUserPassword("SalesPage01");
		user.setUserName("BumKi Cho");
		
		userService = new UserService();
		userService.saveUser(user);
		
	}

}
