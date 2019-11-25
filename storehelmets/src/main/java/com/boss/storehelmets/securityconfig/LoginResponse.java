package com.boss.storehelmets.securityconfig;

public class LoginResponse {
	private String accessToken;
	private String tokenType = "Bearer ";
	
	
	
	public LoginResponse(String accessToken, String tokenType) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
	}

	public LoginResponse(String accessToken) {
	   this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	
	
}
