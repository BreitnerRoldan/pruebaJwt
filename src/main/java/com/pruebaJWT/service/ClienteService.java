package com.pruebaJWT.service;

import java.util.List;

import com.pruebaJWT.entity.Cliente;

public interface ClienteService {
	
	List<Cliente> findAll();
	Cliente Create(Cliente cliente); 
	Cliente update(long id, Cliente cliente); 
	void deleted(long id); 
	

}
