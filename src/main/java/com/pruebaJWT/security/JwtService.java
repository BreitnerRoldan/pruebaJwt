package com.pruebaJWT.security;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*	2.1 Autenticamos al usuario y retornamos un token

 Vamos a crear una clase que gestione todo lo relacionado con el token. En el siguiente ejemplo,
 al crear el token, estoy añadiendo el nombre de usuario como subject, los permisos de ese 
 usuario(solo tenemos uno en la clase MyUserDetails y es ROLE_SENSEI) y la fecha en la que expira. 
 Podemos agregar claims personalizados con claim(key, value) o pasar un mapa de claims, setClaims().
 Para firmar el token simplemente voy a usar «key». En un proyecto real, podría recuperarse dicha 
 key del archivo de configuración de la aplicación.
  
Para leer el token, necesitamos la clave secreta para validar la firma. */

@Service
public class JwtService {

	private static final int EXPIRATION_TIME = 1000*60*60; 
	private static final String AUTHORITIES = "authorities"; 
	private final String SECRET_KEY; 
	
	public JwtService() {
		SECRET_KEY = Base64.getEncoder().encodeToString("key".getBytes()); 
	}
	
	public String createToken(UserDetails userDetails) {
		String username = userDetails.getUsername(); 
		Collection<? extends GrantedAuthority>authorities = userDetails.getAuthorities();
		return Jwts.builder()
				.setSubject(username)
				.claim(AUTHORITIES, authorities)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact(); 
	}
	
	public Boolean hasTokenExpired(String token) {
		return Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody()
				.getExpiration()
				.before(new Date()); 
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		String username = extractUsername(token); 
		return (userDetails.getUsername().equals(username) && !hasTokenExpired(token)); 
		
	}
	
	public String extractUsername(String token) {
		return Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody()
				.getSubject(); 
		
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(String token){
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token).getBody();
		return (Collection<? extends GrantedAuthority>)claims.get(AUTHORITIES); 
	}
	
}
