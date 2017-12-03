package br.edu.univas.si4.model;

public class FuncionarioTO {
	
	private String name;
	private String role;
	private String bairro;
	private String rua;
	private String cpf;
	private String cod;
	private String telefone;
	
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

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getBairro() {
		return bairro;
	}

	public String getRua() {
		return rua;
	}

	public String getCpf() {
		return cpf;
	}

	public String getCod() {
		return cod;
	}

	public String getTelefone() {
		return telefone;
	}

	
	
	
	
	
	

}
