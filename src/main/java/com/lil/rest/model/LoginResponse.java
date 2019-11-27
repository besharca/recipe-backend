package com.lil.rest.model;



public class LoginResponse {
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	private String token;
	private String error;
	public LoginResponse(String token, String error) {
		super();
		this.token = token;
		this.error = error;
	}
	public LoginResponse() {
		super();
	}
	
	
}
