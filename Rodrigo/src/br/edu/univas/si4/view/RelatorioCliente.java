package br.edu.univas.si4.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import br.edu.univas.si4.controller.ClienteController;
import br.edu.univas.si4.model.ClienteDAO;
import br.edu.univas.si4.model.ClienteTO;
import javafx.scene.control.ComboBox;

public class RelatorioCliente extends JPanel {
	
	private ClienteTO clienteTO;
	private ClienteDAO clienteDAO;
	private ClienteController clienteController;
	private JPanel topPanel;
	private JPanel gridBag;
	private JTextField dados;
	private JComboBox<String> comboBox;
	private JTable tableClientes;
	
	
	public RelatorioCliente() {
		this.clienteController = new ClienteController();
		this.clienteDAO = new ClienteDAO();
		this.setLayout(new BorderLayout());
		initializeComponents();
	}
	
	public void initializeComponents() {
		this.setTopPanel();
		this.setCenterPanel();
		
	}
	
	public void setTopPanel() {
		this.topPanel = new JPanel();
		JLabel title = new JLabel("Relatório de clientes");
		title.setFont(new Font("Arial", Font.BOLD, 36));
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
		
		dados = new JTextField();
		dados.setText("Informe nome aqui");
		dados.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				dados.setText("");
			}
		});
		
		dados.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				searchCliente();			
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gridBag.add(dados, gbc);
		
		this.comboBox = new JComboBox();
		this.comboBox.addItem("Nome");
		this.comboBox.addItem("Cidade");
		this.comboBox.addItem("CPF");
		this.comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String opcao = comboBox.getSelectedItem().equals("Cidade") ? "a cidade" : " o " + comboBox.getSelectedItem().toString().toLowerCase();
				dados.setText("Informe "+opcao+" aqui");
				
			}
		});
		gbc.gridx = 2;
		gbc.gridy = 1;
		this.gridBag.add(this.comboBox, gbc);

		
		
		Vector<String> columnNames = new Vector<>();
		columnNames.add("Nome");
		columnNames.add("CPF");
		columnNames.add("Endereço");
		columnNames.add("Cidade");
		columnNames.add("Telefone");
		
 		tableClientes = new JTable(null, columnNames);
		tableClientes.setFillsViewportHeight(true);
  		
		
		JScrollPane tableScroll = new JScrollPane(tableClientes);
		
		tableScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		tableScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1.0;
		gbc.gridwidth = 5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(40, 40, 40, 40);
		gbc.anchor = GridBagConstraints.CENTER;
		this.gridBag.add(tableScroll, gbc);
		
		
		this.add(this.gridBag, BorderLayout.CENTER);
	}
	
	public void searchCliente() {
		String dados = this.dados.getText().toString();
		String opcao = this.comboBox.getSelectedItem().toString();
		
		this.atualizarTabela(this.clienteController.searchCliente(dados, opcao));
	}
	
	public void atualizarTabela(ArrayList<ClienteTO> dados) {
		       
        DefaultTableModel dtm = (DefaultTableModel) tableClientes.getModel();
        dtm.setRowCount(0);
        
        for (ClienteTO cliente : dados) {
                 dtm.addRow(new Object[] {
                    cliente.getNome(),
                    cliente.getCpf(),
                    cliente.getRua(),
                    cliente.getCidade(),
                    cliente.getTelefone()
            });
            
        }
	}
 	
	

}
