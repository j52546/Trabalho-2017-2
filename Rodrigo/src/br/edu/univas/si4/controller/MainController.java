package br.edu.univas.si4.controller;

import javax.swing.JOptionPane;

import br.edu.univas.si4.model.DBException;
import br.edu.univas.si4.model.FuncionarioDAO;
import br.edu.univas.si4.model.FuncionarioTO;
import br.edu.univas.si4.view.CadastroClientePanel;
import br.edu.univas.si4.view.Login;
import br.edu.univas.si4.view.MainView;
import br.edu.univas.si4.view.MenuPanel;
import br.edu.univas.si4.view.RealizarVendaPainel;

public class MainController {
	
	private Login telaLogin;
	private FuncionarioTO funcionario;
	private FuncionarioDAO funcDAO;
	private MainView mainView;
	private CadastroClientePanel cadastroClienteView;
	private RealizarVendaPainel realizarVendaPanel;
	
	
	private MenuPanel menuPanel;
	
	
	public MainController() {
		telaLogin = new Login(this);
		funcDAO = new FuncionarioDAO();
		
	}
	
	public void initialize() {
		this.telaLogin.setVisible(true);
	}
	
	public void authUser(String username, String password) {
		try {
			this.funcionario = funcDAO.authenticateUser(username, password);
			this.mainView = new MainView(this);
			this.mainView.initialize();
			//this.menuViwer.buildMenu();
		} catch (DBException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public MenuPanel telaInicio() {
		if(menuPanel ==  null) {
			menuPanel = new MenuPanel(this.getName());
		}
		
		return menuPanel;
	}
	
	public CadastroClientePanel mudarParaTelaCadastroCliente() throws Exception {
		if(this.cadastroClienteView == null) {
			this.cadastroClienteView = new CadastroClientePanel();
		}
		
		if(!"vendedor".equals(this.funcionario.getRole()) && !"gerente".equals(this.funcionario.getRole())) {
				throw new Exception("Usuario sem permissao");
		}
		
		return this.cadastroClienteView;
	}
	
	public RealizarVendaPainel mudarParaTelaRealizarVendas() throws Exception{
		if(this.realizarVendaPanel == null) {
			this.realizarVendaPanel = new RealizarVendaPainel();
		}
		if(!"vendedor".equals(this.funcionario.getRole()) && !"gerente".equals(this.funcionario.getRole())) {
			throw new Exception("Usuario sem permissao");
	}
		return this.realizarVendaPanel;
	}
	
	public String getName() {
		return this.funcionario.getName();
	}
	

}
