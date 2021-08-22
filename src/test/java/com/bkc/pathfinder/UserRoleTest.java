package com.bkc.pathfinder;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bkc.pathfinder.config.security.PFPasswordEncoder;
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
	
	@Autowired
	private PFPasswordEncoder passwordEncoder;
	
	@Test
	public void testThese() {
		
		save_user_test();
		//get_user_role_test();
		
	}

	public void save_user_test() {
		User user = new User();
		user.setUserPassword("SalesPage01");
		user.setUserName("bcho");
		user.setUserEmail("bumkicho@icloud.com");
		user.setActive(true);
		/* 
		 * WHEN using spring, do not create a new instance yourself. It creates it outside of Spring context.
		 * userService = new UserService();
		 */
		userService.saveUser(user);
		
		User user2 = new User();
		user2.setUserPassword("SalesPage01");
		user2.setUserName("chobk");
		user2.setUserEmail("bumkicho@yahoo.com");
		user2.setActive(false);
		
		userService.saveUser(user2);
		
		Role role = new Role();
		role.setRoleName("ADMIN");
		role.setDescription("Administrator can handle admin tasks.");
		
		userService.saveRole(role);
		
		Role role2 = new Role();
		role2.setRoleName("USER");
		role2.setDescription("User do not handle admin tasks.");
		
		userService.saveRole(role2);
		
		userService.saveUserRole(user, role);
		userService.saveUserRole(user2, role2);
		
	}

	public void get_user_role_test() {
		User user = userService.findByUserName("bcho");
		System.out.println(user.getUserEmail());
		
		List<Role> roles = userService.getRolesByUser(user);
		for(Role role : roles) {
			System.out.println(role.getRoleName());
		}
		
		user = userService.findByUserIdAndPassword(user.getUserId(), user.getUserPassword());
	}

}
