package fr.bridgefield.brr.security.entity;

import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationResponse {

	String accessToken;
	
	Principal principal;

	public AuthenticationResponse(String accessToken, Principal principal) {
		super();
		this.accessToken = accessToken;
		this.principal = principal;
		
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}



	public Principal getPrincipal() {
		return principal;
	}

	public void Principal(Principal principal) {
		this.principal = principal;
	}
	
}
