package com.pruebaJWT.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebaJWT.entity.Cliente;
import com.pruebaJWT.repository.ClienteRepository;
import com.pruebaJWT.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	
	@Override
	public Cliente Create(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente update(long id, Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleted(long id) {
		// TODO Auto-generated method stub
		
	}

}
