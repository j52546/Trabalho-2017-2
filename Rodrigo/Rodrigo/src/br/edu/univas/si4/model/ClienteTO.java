package br.edu.univas.si4.model;

import java.util.Date;

public class ClienteTO {
	
	
	
	private String nome;
	private String cpf;
	private String rua;
	private String telefone;
	private String cidade;
	private Date nascimento;
	
	public ClienteTO(String nome, String cpf, String rua, String cidade, String telefone ,java.util.Date data) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.rua = rua;
		this.cidade = cidade;
		this.telefone = telefone;
		this.nascimento = data;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRua() {
		return rua;
	}

	public String getTelefone() {
		return telefone;
	}


	public String getCidade() {
		return cidade;
	}

	public Date getNascimento() {
		return nascimento;
	}

		

}
