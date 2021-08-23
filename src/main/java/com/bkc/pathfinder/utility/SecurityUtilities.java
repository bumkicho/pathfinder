package com.bkc.pathfinder.utility;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * 
 * @author bumki
 *
 */

/*
 * formatRoleForSecurity formats user role value with ROLE_ prefix
 * it is used in PFUserDetailsService class loadUserByUsername class method
 * and it is used in JwtProvider class getAuthentication method
 * either place, it is used to build PWUserDetails
 * 
 * extractAuthTokenFromRequest is used in JwtProvider class extractClaims method and 
 * InternalAuthenticationFilter class doFilterInternal method
 */
public class SecurityUtilities {

	private String jwtSecret;
	
	public SecurityUtilities(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}

	public static SimpleGrantedAuthority formatRoleForSecurity(String role) {
		if(!role.startsWith(ROLE_PREFIX)) role = ROLE_PREFIX + role;
		return new SimpleGrantedAuthority(role);
	}
	
	public static String extractAuthTokenFromRequest(HttpServletRequest request)
    {
        String bearerToken = request.getHeader(AUTH_HEADER);

        if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith(AUTH_TOKEN_PREFIX))
        {
            return bearerToken.substring(7);
        }
        return null;
    }
	
	public Claims extractClaims(HttpServletRequest request)
    {
        String token = extractAuthTokenFromRequest(request);

        if (token == null)
        {
            return null;
        }

        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
	
	//ROLE_ prefix is necessary for Spring security
	public static final String ROLE_PREFIX = "ROLE_";
    public static final String AUTH_HEADER = "authorization";
    public static final String AUTH_TOKEN_TYPE = "Bearer";
    public static final String AUTH_TOKEN_PREFIX = AUTH_TOKEN_TYPE + " ";
}
