package com.pruebaJWT.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * Vamos a crear un controlador que se encargue del login. authenticate() recibe
 * UsernamePasswordAuthenticationToken para su validación y llama a
 * AuthenticationProvider y delega dicha tarea. Si la validación falla se lanza
 * una excepción, en caso contrario se crea el token y se devuelve al usuario.
 *
 */

@RestController
@RequiredArgsConstructor
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager; 
	@Autowired
	private MyUserDetailService myUserDetailService;
	@Autowired
	private JwtService jwtService;

	
	@PostMapping("/login")
	public AuthenticationResponse createToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword());
			authenticationManager.authenticate(authentication);

		} catch (BadCredentialsException e) {
			throw new Exception("Invalid username or password", e);
		}
		UserDetails userDetails = myUserDetailService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtService.createToken(userDetails);
		return new AuthenticationResponse(token);

	}

}
