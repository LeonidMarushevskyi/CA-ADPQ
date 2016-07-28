package com.portlandwebworks.chhs.authentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author nick
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationResponse {
	
	private String token;
	private String username;
	private List<String> roles;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
}
