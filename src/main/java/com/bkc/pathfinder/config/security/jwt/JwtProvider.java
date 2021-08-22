package com.bkc.pathfinder.config.security.jwt;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bkc.pathfinder.config.security.PFUserDetails;
import com.bkc.pathfinder.utility.SecurityUtilities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author bumki
 *
 */

/*
 * JwtProvider
 * generateToken - set expiration and sign with secret
 * getAuthenticaton - receives HttpServletRequest and returns UsernamePasswordAuthenticationToken(principal, credentials, authorities)
 * validateToken - check expiration
 */

@Component
public class JwtProvider implements JwtProviderInterface {
	
	@Value("${app.jwt.secret}")
	private String JWT_SECRET;
	
	@Value("${app.jwt.expiraton-in-ms}")
	private Long JWT_EXPIRATION_IN_MS;
	
	@Override
	@SuppressWarnings("deprecation")
	public String generateToken(PFUserDetails auth) {
		String authorities = auth.getAuthorities().stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.joining());
		
		return Jwts.builder().setSubject(auth.getUsername())
				.claim("roles", authorities)
				.claim("userId", auth.getUserId())
				.setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET)
				.compact();
	}
	
	@Override
    public Authentication getAuthentication(HttpServletRequest request)
    {
        Claims claims = extractClaims(request);

        if (claims == null)
        {
            return null;
        }

        String username = claims.getSubject();
        String userId = claims.get("userId", String.class);

        Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(SecurityUtilities::formatRoleForSecurity)
                .collect(Collectors.toSet());

        UserDetails userDetails = PFUserDetails.builder()
                .userName(username)
                .authorities(authorities)
                .userId(userId)
                .build();

        if (username == null)
        {
            return null;
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
    }

	@Override
    public boolean validateToken(HttpServletRequest request)
    {
        Claims claims = extractClaims(request);

        if (claims == null)
        {
            return false;
        }

        if (claims.getExpiration().before(new Date()))
        {
            return false;
        }
        return true;
    }

    private Claims extractClaims(HttpServletRequest request)
    {
        String token = SecurityUtilities.extractAuthTokenFromRequest(request);

        if (token == null)
        {
            return null;
        }

        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

}
