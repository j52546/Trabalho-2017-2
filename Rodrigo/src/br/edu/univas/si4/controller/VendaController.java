package br.edu.univas.si4.controller;

import java.util.ArrayList;
import java.util.Date;

import br.edu.univas.si4.model.VendaDAO;
import br.edu.univas.si4.model.VendaTO;

public class VendaController {
	
	private VendaTO vendaTO;
	private VendaDAO vendaDAO;
	
	public VendaController() {
		this.vendaDAO = new VendaDAO();
	}

	public ArrayList<VendaTO> searchFull(String dados, String opcao, Date data_inicio, Date data_final) {
		return this.vendaDAO.searchFull(dados, opcao, data_inicio, data_final);
		
	}

	public ArrayList<VendaTO> getWithClientOrProduct(String dados, String opcao) {
		return this.vendaDAO.getWithClientOrProduct(dados, opcao);
	}

	public ArrayList<VendaTO> getVendas() {
		return this.vendaDAO.getVendas();
	}

	public void RealizarVenda(String cpf, String codigo, int qtd, float preco) {
		vendaDAO.venda(cpf, codigo, qtd, preco);
	}

	public ArrayList<VendaTO> getVendasFalse() {
		return this.vendaDAO.getVendasFalse();
	}

	public void AprovarVenda(int id) {
		this.vendaDAO.aprovarVenda(id);
		
	}

	public ArrayList<VendaTO> getVendasAprovadoOrReprovado(String condition) {
		return this.vendaDAO.getVendasAprovadoOrReprovado(condition);
	}
	

}
