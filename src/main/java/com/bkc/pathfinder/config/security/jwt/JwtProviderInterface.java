package com.bkc.pathfinder.config.security.jwt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.bkc.pathfinder.config.security.PFUserDetails;

/**
 * 
 * @author bumki
 *
 */
public interface JwtProviderInterface {

	String generateToken(PFUserDetails auth);

	Authentication getAuthentication(HttpServletRequest request);

	boolean validateToken(HttpServletRequest request);

}
