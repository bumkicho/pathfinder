package com.bkc.pathfinder.config.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bkc.pathfinder.model.user.User;

import lombok.*;

/**
 * 
 * @author bumki
 *
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PFUserDetails implements UserDetails {
	
	private String userId;
	private String userName;
	transient private String userPassword; //don't show up on serialized places
	private User user; //user for only login operation, don't use in JWT
	private boolean active;
	private Set<GrantedAuthority> authorities;
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return userPassword;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
