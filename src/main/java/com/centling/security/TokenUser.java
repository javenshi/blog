package com.centling.security;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class TokenUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7958134989538914636L;

	public TokenUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {

		super(username, password, authorities);
	}
	
	private Integer userId = null;
	
	private User user = null;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
