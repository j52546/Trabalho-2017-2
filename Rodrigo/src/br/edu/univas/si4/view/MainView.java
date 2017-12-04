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
	
	private GridBagConstraints buttonConstraint(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.insets = new Insets(20, 5, 20, 5);
		return gbc;
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
	
	private void mudarParatelelistaClientes() {
		this.getContentPane().removeAll();
		try {
		this.getContentPane().add(this.mainController.mudarParatelelistaCliente(), BorderLayout.CENTER);
		} catch (Exception e) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, "Acesso Negado", e.getMessage(), JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, "Acesso Negado", e.getMessage(), JOptionPane.ERROR_MESSAGE);
			this.getContentPane().add(this.mainController.telaInicio(), BorderLayout.CENTER);
		}
		this.revalidate();
		this.repaint();
	}
	
	private void telaExcluirCliente() {
		this.getContentPane().removeAll();
		try {
			this.getContentPane().add(this.mainController.mudarParaTelaExcluirCliente(),BorderLayout.CENTER);
		} catch(Exception e) {
			
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Acesso Negado", e.getMessage(), JOptionPane.ERROR_MESSAGE);
			this.getContentPane().add(this.mainController.telaInicio(), BorderLayout.CENTER);
		}
		this.revalidate();
		this.repaint();
	
	}
	
	private void telaRelatorioCliente() {
		this.getContentPane().removeAll();
		try {
			this.getContentPane().add(this.mainController.mudarParaTelaRelatorioCliente(),BorderLayout.CENTER);
		} catch(Exception e) {
			
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Acesso Negado", e.getMessage(), JOptionPane.ERROR_MESSAGE);
			this.getContentPane().add(this.mainController.telaInicio(), BorderLayout.CENTER);
		}
		this.revalidate();
		this.repaint();
	
	}
	
	private void mudarParaTelaAprovacao() {
		this.getContentPane().removeAll();
		try {
			this.getContentPane().add(this.mainController.mudarParaTelaAprovacao(), BorderLayout.CENTER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Acesso Negado", e.getMessage(), JOptionPane.ERROR_MESSAGE);
			this.getContentPane().add(this.mainController.telaInicio(), BorderLayout.CENTER);
		}
		this.revalidate();
		this.repaint();
}
	
	private void telaRelatorioFuncionario() {
		this.getContentPane().removeAll();
		try {
			this.getContentPane().add(this.mainController.mudarParaTelaRelatorioFuncionario(),BorderLayout.CENTER);
		} catch(Exception e) {
			
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Acesso Negado", e.getMessage(), JOptionPane.ERROR_MESSAGE);
			this.getContentPane().add(this.mainController.telaInicio(), BorderLayout.CENTER);
		}
		this.revalidate();
		this.repaint();
	
	}
	
	private void telaRelatorioVenda() {
		this.getContentPane().removeAll();
		try {
			this.getContentPane().add(this.mainController.mudarParaTelaRelatorioVenda(),BorderLayout.CENTER);
		} catch(Exception e) {
			
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Acesso Negado", e.getMessage(), JOptionPane.ERROR_MESSAGE);
			this.getContentPane().add(this.mainController.telaInicio(), BorderLayout.CENTER);
		}
		this.revalidate();
		this.repaint();
	
	}
	
	
	
	private JPanel getCenterPanel() {
		return centerPanel;
	}

	public void buildMenu() {
		
		
		JMenu inicio = new JMenu("Inicio");
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
		menu.addSeparator();

		mi = new JMenuItem("Lista Clientes");

		mi.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					mudarParatelelistaClientes();
					
				}
			});
		menu.add(mi);
		menubar.add(menu);
		menu.addSeparator();

		mi = new JMenuItem("Excluir Cliente");
		mi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				telaExcluirCliente();
			}
		});
		menu.add(mi);
		menubar.add(menu);
		menu.addSeparator();

		menu = new JMenu("Vendas");

		mi = new JMenuItem("Realizar Vendas");
		mi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarParaTelaRealizarVendas();
			}
		});
		menu.add(mi);
		menu.addSeparator();
		mi = new JMenuItem("Aprovar Vendas");
		mi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarParaTelaAprovacao();
				
			}
		});
		menu.add(mi);
		menu.addSeparator();
		menubar.add(menu);
		
		menu = new JMenu("Relatórios");

		mi = new JMenuItem("Relatório de cliente");
		mi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				telaRelatorioCliente();
			}
		});
		menu.add(mi);
		menu.addSeparator();
		mi = new JMenuItem("Relatório de vendas");
		mi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				telaRelatorioVenda();
			}
		});
		menu.add(mi);
		menu.addSeparator();
		menubar.add(menu);

		mi = new JMenuItem("Listar funcionário");
		mi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				telaRelatorioFuncionario();
			}
		});
		menu.add(mi);
		menu.addSeparator();
		menubar.add(menu);

	
		
	
	}	
		
}
