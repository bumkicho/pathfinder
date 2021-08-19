package com.bkc.pathfinder.model.authorization;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder extends BCryptPasswordEncoder {

	public PasswordEncoder() {
		super();
	}	

}
