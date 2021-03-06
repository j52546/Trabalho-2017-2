package br.edu.univas.si4.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.edu.univas.si4.model.ClienteDAO;
import br.edu.univas.si4.model.ClienteTO;
import br.edu.univas.si4.view.CadastroClientePanel;

public class ClienteController {
	
	private CadastroClientePanel cadastroPanel;
	private MainController mainController;
	private ClienteDAO clienteDAO;
	
	public ClienteController() {
		mainController = new MainController();
		clienteDAO = new ClienteDAO();
	}
	
	public void cadastrarCliente(ClienteTO clientTo) {
		clienteDAO.cadastrarCliente(clientTo);
		
	}
	
	public ArrayList<ClienteTO> getClientes() {
		return this.clienteDAO.getClientes();	
	}
	
	public void excluirCliente(String cpf) {
		this.clienteDAO.deleteCliente(cpf);
	}

	public ArrayList<ClienteTO> searchCliente(String dados, String opcao) {
		return this.clienteDAO.searchCliente(dados, opcao);
		
	}

	public ClienteTO findOne(String cliente) {
			return this.clienteDAO.findOne(cliente);
	}

	public ClienteTO findOneCPF(String cliente) {
		return this.clienteDAO.findOneCPF(cliente);
	}
	

}
