package com.pruebaJWT.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * Para crear una clase de seguridad personalizada, necesitamos usar @EnableWebSecurity y extender 
 * la clase con @WebSecurityConfigurerAdapter para que podamos redefinir algunos de los métodos
 *  proporcionados. Spring Security te fuerza a hashear las contraseñas para que no se guarden en texto plano. 
 *  Para los siguientes ejemplos, vamos a usar PasswordEncoder, aunque no debe ser una opción para proyectos 
 *  reales, pero para este ejercicio es mas que suficiente. Una alternativa podría ser BCryptPasswordEncoder.
 */

//----------------------------------Autenticacion--------------------------
@SuppressWarnings("deprecation")
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MyUserDetailService myUserDetailService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
//.........las credenciales del usuario se almacenaran en memoria...........
		auth.userDetailsService(myUserDetailService); 
		/**auth.inMemoryAuthentication()
			.withUser("user1").password("123").roles("APPRENTICE")
			.and()
			.withUser("user2").password("123").roles("SENSEI"); */
	}
	
//---------------------------------Autorizacion------------------------------	
//se definen que recursos deben estar securizados y cuales no.---------------
	/**@Override
	public void configure(HttpSecurity http)throws Exception{
	http.authorizeRequests()
		.antMatchers("/sensei").hasRole("SENSEI")
		.antMatchers("/apprentice").hasRole("APPRENTICE")
		.antMatchers("/").permitAll()
		.and().formLogin(); */
	@Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/login").permitAll()
                .anyRequest().authenticated();
    }
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		
		
		/*En la clase WebScurity, debemos hacer el override de authenticationManagerBean si queremos 
		 * inyectarlo(autowired) en UserController. Vemos cómo se han dado permisos para acceder al 
		 * endpoint /login pero cualquier otro recurso está protegido.
		 */
		
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
		
	}
	
	}
	