package br.edu.univas.si4.model;

public class FuncionarioTO {
	
	private String name;
	private String role;
	
	public FuncionarioTO(String name, String role) {
		this.role = role;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public String getRole() {
		return role;
	}
	
	

}
