package com.bkc.pathfinder.config.security.internal;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import lombok.extern.slf4j.Slf4j;

import com.bkc.pathfinder.config.security.PFUserDetails;
import com.bkc.pathfinder.config.security.jwt.JwtProviderInterface;
import com.bkc.pathfinder.model.user.User;
import com.bkc.pathfinder.service.user.UserService;
import com.bkc.pathfinder.utility.SecurityUtilities;

import io.jsonwebtoken.Claims;

@Slf4j
public class InternalAuthenticationFilter extends OncePerRequestFilter {
	
	@Value("${app.jwt.secret}")
	private String JWT_SECRET;
	
	private final String accessKey;
	
	@Autowired
	private UserService userService;
	
	public InternalAuthenticationFilter(String accessKey) {
		this.accessKey = accessKey;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getRequestURI().startsWith("/api/internal");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {		
		try {
			String jwtToken = SecurityUtilities.extractAuthTokenFromRequest(request);
			if(jwtToken == null || !jwtToken.equals(accessKey)) {
				Claims claims = getSecurityUtilities().extractClaims(request);

		        if (claims != null)
		        {
		        	// String username = claims.getSubject();
		        	// String userId = claims.get("userId", String.class);		        	
		        	Boolean isAdmin=Arrays.stream(claims.get("roles").toString().split(",")).anyMatch(role->role.equalsIgnoreCase("ROLE_ADMIN"));
		        	if(!isAdmin) {
		        		log.warn("Access to uri: {} is denied", request.getRequestURI());
						throw new RuntimeException("UNAUTHORIZED");
		        	}		        	
		        } else {
					log.warn("Internal endpoint requested without/wrong key uri: {}", request.getRequestURI());
					throw new RuntimeException("UNAUTHORIZED");
		        }
			}
			
			// if jwtToken equals accessKey or request user is Admin user in crm
	
			Set<GrantedAuthority> authorities = Set.of(SecurityUtilities.formatRoleForSecurity(TMP_INTERNAL_ROLE));
	
			PFUserDetails tempAdmin = PFUserDetails.builder()
									.userId("sys")
									.userName("Sys Admin")
									.userPassword("SysAdmin01")
									.authorities(authorities)
									.build();
			
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(tempAdmin, null, tempAdmin.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		} catch (Exception ex) {
			log.error("InternalAuthenticationFilter: Could not set user authentication in security context", ex);
		}
		
		filterChain.doFilter(request, response);
	}
	
	@Bean
	private SecurityUtilities getSecurityUtilities() {
		return new SecurityUtilities(JWT_SECRET);
	}
	
	private static String TMP_INTERNAL_ROLE = "SYSADMIN"; 

}
