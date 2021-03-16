package com.pruebaJWT.model;


public class ClienteModel {

	
	private long id;
	private String name;
	private String telefono; 
	
	public ClienteModel() {
		// TODO Auto-generated constructor stub
	}

	public ClienteModel(long id, String name, String telefono) {
		this.id = id;
		this.name = name;
		this.telefono = telefono;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	
}
