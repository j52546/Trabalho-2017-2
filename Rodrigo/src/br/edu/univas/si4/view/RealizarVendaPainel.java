package br.edu.univas.si4.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Date;

import br.edu.univas.si4.controller.ClienteController;
import br.edu.univas.si4.controller.ProdutoController;
import br.edu.univas.si4.controller.VendaController;
import br.edu.univas.si4.model.ClienteDAO;
import br.edu.univas.si4.model.ClienteTO;
import br.edu.univas.si4.model.DBUtil;
import br.edu.univas.si4.model.ProdutoTO;
import br.edu.univas.si4.model.VendaTO;
import javafx.scene.control.ComboBox;




public class RealizarVendaPainel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5503683771232720126L;
	private JPanel topPanel;
	private JPanel gridBag;
	private JTextField dados;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox2;
	private JTable tablePedido;
	private JTextField qtd;
	private JTextField valor;
	private String atuQtdDis;
	ArrayList<ClienteTO> clientes;
	ArrayList<ProdutoTO> produtos;
	
	
	
	private ClienteController clienteController;
	private ProdutoController produtoController;
	private VendaController vendaController;


	public RealizarVendaPainel() {
		
		this.clienteController = new ClienteController();
		this.produtoController = new ProdutoController();
		this.vendaController = new VendaController();
		
		this.clientes = new ArrayList<>();
		this.produtos = new ArrayList<>();
		
		
		this.setLayout(new BorderLayout());
		initializeComponents();
		
	}

	

	public void initializeComponents() {
		this.setTopPanel();
		this.setCenterPanel();
		
	}

	public void setTopPanel() {
		this.topPanel = new JPanel();
		JLabel title = new JLabel("Realizar Vendas");
		title.setFont(new Font("Arial", Font.BOLD, 46));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		this.topPanel.add(title);
		this.add(topPanel, BorderLayout.NORTH);
	
	}
	
	public void setCenterPanel() {
		this.gridBag = new JPanel();
		this.gridBag.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel information = new JLabel("Informe algum dos dados a seguir");
		
		information.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 20, 5, 0);
		this.gridBag.add(information, gbc);
		
		this.comboBox = new JComboBox();
		this.comboBox.addItem("Nome do cliente");
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.gridBag.add(this.comboBox, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		
		
		this.comboBox2 = new JComboBox();
		this.comboBox2.addItem("Nome do Produto");
		this.comboBox2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				atuQtdDis = (String) comboBox2.getSelectedItem();
				
			}
		});
		gbc.gridx = 2;
		gbc.gridy = 1;
		this.gridBag.add(this.comboBox2, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 1;
		
			
		gbc.gridx = 4;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		qtd = new JTextField();
		qtd.setText("Informe a quantidade vendida");
		qtd.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				qtd.setText("");
			}
		});
		
		this.gridBag.add(this.qtd, gbc);
		
		gbc.gridx = 5;
		gbc.gridy = 1;
		
		
		//============
		valor = new JTextField();
		valor.setText("Informe o valor do item");
		valor.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				valor.setText("");
			}
		});
		
		this.gridBag.add(this.valor, gbc);
		
		gbc.gridx = 6;
		gbc.gridy = 1;
		
		
		//=============
		
		
		//====================
		
		JButton search = new JButton("Vender");
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setPedido();
			}
		});
		this.gridBag.add(search, gbc);
		
		
		Vector<String> columnNames = new Vector<>();
		columnNames.add("CPF");
		columnNames.add("Nome Produto");
		columnNames.add("Quantidade");
		columnNames.add("Preco");
		
		tablePedido = new JTable(null, columnNames);
		tablePedido.setFillsViewportHeight(true);
  		
		
		JScrollPane tableScroll = new JScrollPane(tablePedido);
		
		tableScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		tableScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1.0;
		gbc.gridwidth = 7;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(40, 40, 40, 40);
		gbc.anchor = GridBagConstraints.CENTER;
		this.gridBag.add(tableScroll, gbc);
		
		
		this.add(this.gridBag, BorderLayout.CENTER);
	}
	
	
	public void getDados() {
		limparClienteJTable();
		clientes = this.clienteController.getClientes();
		produtos = this.produtoController.getProdutos();
		
		comboBox.removeAllItems();
		comboBox2.removeAllItems();
			
		for(ProdutoTO produto : produtos) {
			comboBox2.addItem(produto.getNome());
		}
		for(ClienteTO cliente : clientes) {
			comboBox.addItem(cliente.getCpf());
		}
	
	}
	
		
	public void setPedido() {
            
		String cpf = comboBox.getSelectedItem().toString();
		String nomeProdudo = comboBox2.getSelectedItem().toString();
		ProdutoTO nomeProduto = produtoController.findOneName(nomeProdudo);
		System.out.println(valor.getText());
		atualizarTabela(cpf, nomeProdudo, qtd.getText(), valor.getText());
		vendaController.RealizarVenda(cpf, nomeProduto.getCodigo(), Integer.parseInt(qtd.getText().toString()), Float.parseFloat(valor.getText().toString()));
			
	}
	
	 public void atualizarTabela(String cpf, String nome, String qtd, String preco) {
										
					DefaultTableModel dtm = (DefaultTableModel) tablePedido.getModel();
													
							dtm.addRow(new Object[] {
									cpf,
									nome,
									qtd,
									preco,
							});
							
	}
	 
	 private void limparClienteJTable() {
		 DefaultTableModel tableModel = (DefaultTableModel) tablePedido.getModel();
		 while (tableModel.getRowCount() > 0) {
		 tableModel.removeRow(0);
		 }
		}
	 
		
	
	
            
}
    



