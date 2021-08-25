package com.bkc.pathfinder.config.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PFPasswordEncoder extends BCryptPasswordEncoder {

	public PFPasswordEncoder() {
		super();
	}

	public static final String DEFAULT_PASSWORD = "defaultPassword!";
}
