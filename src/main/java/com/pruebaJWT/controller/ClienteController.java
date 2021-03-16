package com.pruebaJWT.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebaJWT.entity.Cliente;
import com.pruebaJWT.model.ClienteModel;
import com.pruebaJWT.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteModel>> listar(){
		List<Cliente> cliente = clienteService.findAll();
		List<ClienteModel> cliMo = new ArrayList<ClienteModel>();
		
		for(Cliente cli : cliente) {
			cliMo.add(new ClienteModel(cli.getId(), cli.getName(), cli.getTelefono())); 			
			
		}
		return new ResponseEntity<List<ClienteModel>>(cliMo, HttpStatus.OK); 
	}
	
}

