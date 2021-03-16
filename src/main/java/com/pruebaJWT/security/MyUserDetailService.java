package com.pruebaJWT.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


//---Vamos a configurar Spring Security para que dependa de UserDetailsService, un servicio que nos permitirá cargar--
//---datos específicos del usuario, en la clase WebSecurity-----------------------------

//--Procedemos a crear nuestra propia clase UserDetailService y UserDetails. En MyUserDetailService simplemente-------
//--sobrescribimos el método loadUserByUsername que recibe el nombre de usuario por parámetro.------------------------
@Service
public class MyUserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new MyUserDetails();
	}
}


