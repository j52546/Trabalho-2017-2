package br.edu.univas.si4.controller;

import java.util.ArrayList;

import br.edu.univas.si4.model.ClienteTO;
import br.edu.univas.si4.model.VendaDAO;
import br.edu.univas.si4.model.VendaTO;

public class VendaController {

	private VendaTO vendaTO;
	private VendaDAO vendaDAO;
	private Object clienteDAO;
	
	public VendaController() {
		this.vendaDAO = new VendaDAO();
	}
	
	public ArrayList<VendaTO> getVendas(){
		return this.vendaDAO.getVendas();
	}
	
	public void RealizarVenda(String cpf, String codigo, int qtd, float preco) {
		vendaDAO.venda(cpf, codigo, qtd, preco);
	}
	
	public void AprovarVenda(Integer id) {
		this.vendaDAO.aprovarPedido(id);
	}
	
	
}
