package br.edu.univas.si4.controller;

import java.util.ArrayList;

import br.edu.univas.si4.model.ClienteTO;
import br.edu.univas.si4.model.ProdutoDAO;

import br.edu.univas.si4.model.ProdutoTO;

public class ProdutoController {

	private MainController mainController;
	private ProdutoDAO produtoDAO;
	
	public ProdutoController() {
		
		mainController = new MainController();
		produtoDAO = new ProdutoDAO();
	}
	
	public ArrayList<ProdutoTO> getProdutos() {
		return this.produtoDAO.findAll();	
	}
	
	public ProdutoTO findOneName(String nome) {
		return this.produtoDAO.findOneName(nome);
	}
}
