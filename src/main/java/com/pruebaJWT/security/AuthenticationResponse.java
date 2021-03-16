package com.pruebaJWT.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//Si la autenticación es correcta, enviamos el token (AuthenticationResponse).
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

	private String token; 
	
	public AuthenticationResponse() {
		// TODO Auto-generated constructor stub
	}

	public AuthenticationResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}

