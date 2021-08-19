package com.bkc.pathfinder.model.authorization;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder extends BCryptPasswordEncoder {

	public PasswordEncoder() {
		super();
	}	

}
