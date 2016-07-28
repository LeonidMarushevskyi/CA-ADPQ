package com.portlandwebworks.chhs.authentication;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author nick
 */
public class AuthenticatedUser implements UserDetails {

	private String token = "TEST"; 
	private final String username;
	private final List<String> roles;

	public AuthenticatedUser(String username, List<String> roles) {
		this.username = username;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return "";
	}

	@Override
	public String getUsername() {
		return username;
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
