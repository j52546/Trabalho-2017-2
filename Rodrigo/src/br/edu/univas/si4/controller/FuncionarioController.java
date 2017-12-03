package br.edu.univas.si4.controller;

import java.util.ArrayList;

import br.edu.univas.si4.model.FuncionarioDAO;
import br.edu.univas.si4.model.FuncionarioTO;

public class FuncionarioController {
	
	private FuncionarioTO funcionarioTO;
	private FuncionarioDAO funcionarioDAO;
	
	public FuncionarioController() {
		this.funcionarioDAO = new FuncionarioDAO();
	}
	

	public ArrayList<FuncionarioTO> searchFuncionario(String dados, String opcao) {
		return this.funcionarioDAO.searchFuncionario(dados, opcao);
	}
	
	public ArrayList<FuncionarioTO> findAll() {
		return this.funcionarioDAO.findAll();
	}

}
