package fr.bridgefield.brr.security.entity;

import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationResponse {

	String accessToken;
	
	User user;

	public AuthenticationResponse(String accessToken, User user) {
		super();
		this.accessToken = accessToken;
		this.user = user;
		
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User principal) {
		this.user = principal;
	}
	
}
