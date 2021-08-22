package com.bkc.pathfinder.config.security;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bkc.pathfinder.model.user.Role;
import com.bkc.pathfinder.model.user.User;
import com.bkc.pathfinder.model.user.UserRole;
import com.bkc.pathfinder.service.user.UserService;
import com.bkc.pathfinder.utility.SecurityUtilities;

/**
 * 
 * @author bumki
 *
 */

/*
 * UserDetailsService will loadUserByUsername
 * returns UserDetails using UserDetails builder
 * 
 * WebSecurityConfigurerAdapter needs UserDetailsService/PasswordEncoder in its AuthenticationManagerBuilder config -- see SecurityConfig class
 */

@Service
public class PFUserDetailsService implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DisabledException {
		
		User user = userService.findByUserName(username);
		if(user==null) {
			throw new UsernameNotFoundException("User with "+username+" was not found. Please sign up.");
		} else if(!user.isActive()) {
			throw new DisabledException("Inactive user was found");
		}
		
		//user = userService.findByUserIdAndPassword(user.getUserId(), user)
		List<Role> roles = userService.getRolesByUser(user);

		Set<GrantedAuthority> authorities = new HashSet<>();
		for(Role role : roles) {
			authorities.add(SecurityUtilities.formatRoleForSecurity(role.getRoleName()));
		}
		
		return PFUserDetails.builder()
				.user(user)
				.userId(user.getUserId())
				.userName(user.getUserName())
				.userPassword(user.getUserPassword())
				.authorities(authorities)
				.build();
	}

}
