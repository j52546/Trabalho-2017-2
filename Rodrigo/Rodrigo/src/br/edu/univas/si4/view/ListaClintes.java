package br.edu.univas.si4.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import br.edu.univas.si4.controller.ClienteController;
import br.edu.univas.si4.model.ClienteTO;

public class ListaClintes extends JPanel {
	
	/**
	 * Classe para Trazer a relação de clientes cadastrados 
	 */
	private static final long serialVersionUID = 6777827662866118885L;
	
	private JTable tableClientes;
	private ClienteController clienteController;
	private ClienteTO clienteTO;
	ArrayList<ClienteTO> clientes = new ArrayList<>();

	public ListaClintes(){
		this.clienteController = new ClienteController();
		addComponents();

	}

		private void addComponents() {
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		

		JLabel textLabel = new JLabel();
		textLabel.setText("");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.insets = new Insets(15, 15, 15, 15);
		this.add(textLabel, gbc);
		
		
		Vector<String> columnNames = new Vector<>();
		columnNames.add("Nome");
		columnNames.add("CPF");
		columnNames.add("Endere�o");
		columnNames.add("Cidade");
		columnNames.add("Telefone");
		
		
  		
		tableClientes = new JTable(null, columnNames);
		tableClientes.setFillsViewportHeight(true);
  		
		
		JScrollPane tableScroll = new JScrollPane(tableClientes);
		
		tableScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		tableScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(40, 40, 40, 40);
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(tableScroll, gbc);
		
			 
	}
		//--------------------
		public void getClientes() {
	        this.clientes = this.clienteController.getClientes();
	            
	            
	            DefaultTableModel dtm = (DefaultTableModel) tableClientes.getModel();
	            dtm.setRowCount(0);
	            
	            for (ClienteTO cliente : this.clientes) {
	                dtm.addRow(new Object[] {
	                        cliente.getNome(),
	                        cliente.getCpf(),
	                        cliente.getRua(),
	                        cliente.getCidade(),
	                        cliente.getTelefone()
	                });
	                
	            }
	    }
		//-------------------

}	
