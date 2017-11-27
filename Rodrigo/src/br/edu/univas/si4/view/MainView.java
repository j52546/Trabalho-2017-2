package br.edu.univas.si4.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.xml.ws.WebServiceRefs;

import com.sun.glass.events.KeyEvent;

import br.edu.univas.si4.controller.MainController;

public class MainView extends JFrame {
	
	
	
	private static final long serialVersionUID = -5348235594859732161L;
	
	private JPanel centerPanel;
	private MenuPanel menuPanel;
	private MainController mainController;
	
	JMenuBar menubar;
	JMenu menu, submenu;
	JMenuItem mi, sair, inicioTela;
	
	
	
	public MainView(MainController mainController) {
		menubar = new JMenuBar();
		setJMenuBar(menubar);
		buildMenu();
		
		this.mainController = mainController;
		this.setTitle("Distribuidora");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setPanel();
		addComponents();
		
		
	}
	
	private void setPanel() {
		this.getContentPane().setLayout(new BorderLayout());
		
		centerPanel = new JPanel();
	}
	
	public void initialize() {
		this.setVisible(true);
	}

	private void addComponents() {
			menuPanel = new MenuPanel(this.mainController.getName());
			this.getContentPane().add(menuPanel, BorderLayout.CENTER);

	}
	


	
	private void mudarParaTelaInicio() {
		this.getContentPane().removeAll();
		this.getContentPane().add(this.mainController.telaInicio(), BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}
	
	private void mudarParaTelaCadastroCliente() {
		this.getContentPane().removeAll();
		try {
			this.getContentPane().add(this.mainController.mudarParaTelaCadastroCliente(), BorderLayout.CENTER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Acesso Negado", JOptionPane.ERROR_MESSAGE);
			this.getContentPane().add(this.mainController.telaInicio(), BorderLayout.CENTER);
		}
		this.revalidate();
		this.repaint();
	}
	
	private void mudarParaTelaRealizarVendas() {
		this.getContentPane().removeAll();
		try {
			this.getContentPane().add(this.mainController.mudarParaTelaRealizarVendas(),BorderLayout.CENTER);
		} catch(Exception e) {
			
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Acesso Negado", JOptionPane.ERROR_MESSAGE);
			this.getContentPane().add(this.mainController.telaInicio(), BorderLayout.CENTER);
		}
		this.revalidate();
		this.repaint();
	}
	
	private JPanel getCenterPanel() {
		return centerPanel;
	}
	
	
	
	
	
	


	public void buildMenu() {
		menu = new JMenu("Cliente");

		mi = new JMenuItem("Cadastrar");
		mi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarParaTelaCadastroCliente();
			}
		});
		
		
		menu.add(mi);
		menubar.add(menu);

		mi = new JMenuItem("Lista Clientes");
		menu.add(mi);
		menubar.add(menu);
		menu.addSeparator();

		mi = new JMenuItem("Excluir Cliente");
		menu.add(mi);
		menubar.add(menu);
		menu.addSeparator();

		mi = new JMenuItem("Close");
		menu.add(mi);
		menubar.add(menu);

		menu = new JMenu("Vendas");

		mi = new JMenuItem("Realizar Vendas");
		menu.add(mi);
		mi = new JMenuItem("Aprovar Vendas");
		menu.add(mi);

		JMenu med = new JMenu("Edit");
		mi = new JMenuItem("Copy");
		med.add(mi);
		mi = new JMenuItem("Paste");
		med.add(mi);
		menu.add(med);
		menubar.add(menu);
		
		JMenu inicio = new JMenu("Início");
		this.inicioTela = new JMenuItem("Home");
		this.inicioTela.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarParaTelaInicio();
			}
		});
		inicio.add(this.inicioTela);
		
		this.sair = new JMenuItem("Sair");
		this.sair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		inicio.add(this.sair);
		
		
		menubar.add(inicio);
	}
	
		
}
