package com.bkc.pathfinder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bkc.pathfinder.model.user.*;
import com.bkc.pathfinder.repository.user.UserRepository;
import com.bkc.pathfinder.service.user.UserService;

/**
 * 
 * @author bumki
 *
 */

@SpringBootTest
public class UserRoleTest {
	
	@Autowired
	private UserService userService;

	
	@Test
	public void save_user_test() {
		User user = new User();
		user.setUserId("bcho");
		user.setUserPassword("SalesPage01");
		user.setUserName("BumKi Cho");
		/* 
		 * WHEN using spring, do not create a new instance yourself. It creates it outside of Spring context.
		 * userService = new UserService();
		 */
		userService.saveUser(user);
		
	}

}
