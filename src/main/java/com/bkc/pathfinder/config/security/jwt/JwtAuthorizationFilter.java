package com.bkc.pathfinder.config.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 
 * @author bumki
 *
 */
/*
 * This will be a bean instance within SecurityConfig class
 * Filter base class that aims to guarantee a single execution per request dispatch, on any servlet container.
 * It provides a doFilterInternalmethod with HttpServletRequest and HttpServletResponse arguments.
 * From HttpServletRequest, using JwtProviderInterface, we can extract Authentication and validate its token.
 * 
 * It will be intercepting requests before UsernamePasswordAuthenticationFilter is called
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtProviderInterface jwtProviderInterface;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Authentication auth = jwtProviderInterface.getAuthentication(request);
		
		if(auth!=null && jwtProviderInterface.validateToken(request)) {
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(request, response);		
	}

}
