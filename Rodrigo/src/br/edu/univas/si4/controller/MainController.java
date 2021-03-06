package br.edu.univas.si4.controller;

import java.awt.Component;

import javax.swing.JOptionPane;

import br.edu.univas.si4.model.DBException;
import br.edu.univas.si4.model.FuncionarioDAO;
import br.edu.univas.si4.model.FuncionarioTO;
import br.edu.univas.si4.view.AprovarVenda;
import br.edu.univas.si4.view.CadastroClientePanel;
import br.edu.univas.si4.view.ExcluirCliente;
import br.edu.univas.si4.view.FuncionarioView;
import br.edu.univas.si4.view.ListaClintes;
import br.edu.univas.si4.view.Login;
import br.edu.univas.si4.view.MainView;
import br.edu.univas.si4.view.MenuPanel;
import br.edu.univas.si4.view.RealizarVendaPainel;
import br.edu.univas.si4.view.RelatorioCliente;
import br.edu.univas.si4.view.RelatorioVenda;

public class MainController {
	
	private Login telaLogin;
	private FuncionarioTO funcionario;
	private FuncionarioDAO funcDAO;
	private MainView mainView;
	private CadastroClientePanel cadastroClienteView;
	private RealizarVendaPainel realizarVendaPanel;
	private ListaClintes listaClientes;
	private ExcluirCliente excluirCliente;
	private RelatorioCliente relatorioCliente;
	private FuncionarioView funcionarioView;
	private RelatorioVenda relatorioVenda;
	private AprovarVenda aprovarVenda;
	
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
			this.telaLogin.dispose();
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
		this.realizarVendaPanel.getDados();
		return this.realizarVendaPanel;
	}

	
	public ExcluirCliente mudarParaTelaExcluirCliente() throws Exception {
		if(this.excluirCliente == null) {
			this.excluirCliente = new ExcluirCliente();
		}
		
		if(!"gerente".equals(this.funcionario.getRole())) {
			throw new Exception("Usuario sem permissao");
		}
		
		this.excluirCliente.getClientes();
		return this.excluirCliente;
	}
	
	public ListaClintes mudarParatelelistaCliente(){
		if(this.listaClientes == null) {
			this.listaClientes = new ListaClintes();
		}
		this.listaClientes.getClientes();
		return this.listaClientes;
	}
	
	public AprovarVenda mudarParaTelaAprovacao() throws Exception {
		if(this.aprovarVenda == null) {
			this.aprovarVenda = new AprovarVenda();
		}
		if(!"financeiro".equals(this.funcionario.getRole()) && !"gerente".equals(this.funcionario.getRole())) {
			throw new Exception("Usuario sem permissao");
		}
		this.aprovarVenda.getVendas();
		return this.aprovarVenda;
	}
	

	public String getName() {
		return this.funcionario.getName();
	}

	public RelatorioCliente mudarParaTelaRelatorioCliente() throws Exception {
		if(this.relatorioCliente == null) {
			this.relatorioCliente = new RelatorioCliente();
		}
		
		if(!"gerente".equals(this.funcionario.getRole())) {
			throw new Exception("Usuário sem permissão");
		}
		return this.relatorioCliente;
	}

	public Component mudarParaTelaRelatorioFuncionario() throws Exception {
		if(this.funcionarioView == null) {
			this.funcionarioView = new FuncionarioView();
		}
		
		if(!"gerente".equals(this.funcionario.getRole())) {
			throw new Exception("Usuário sem permissão");
		}
		this.funcionarioView.atualizarTabela(new FuncionarioController().findAll());
		return this.funcionarioView;
	}
	
	public Component mudarParaTelaRelatorioVenda() throws Exception {
		if(this.relatorioVenda == null) {
			this.relatorioVenda = new RelatorioVenda();
		}
		
		if(!"gerente".equals(this.funcionario.getRole())) {
			throw new Exception("Usuário sem permissão");
		}
		this.relatorioVenda.getVendas();
		return this.relatorioVenda;
	}
}
