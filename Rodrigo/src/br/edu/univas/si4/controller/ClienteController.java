package br.edu.univas.si4.controller;

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
	
	

}
