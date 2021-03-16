package com.pruebaJWT.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**Cuando implementamos la interfaz UserDetails, podemos sobrescribir varios métodos.
Voy a crear un campo ‘username’ para obtener el nombre de usuario. El resto de los 
métodos tendrá valores hardcoded. getAuthorities devuelve los permisos otorgados 
al usuario, en este caso añadiré solo el rol SENSEI. La contraseña tendrá el valor ‘pass’.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUserDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;

	@Override
	public Collection<SimpleGrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_SENSEI"));
	}

	@Override
	public String getPassword() {
		return "pass";
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
