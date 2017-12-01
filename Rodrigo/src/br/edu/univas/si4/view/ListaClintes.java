package br.edu.univas.si4.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class ListaClintes extends JPanel {
	
	/**
	 * Classe para Trazer a relaçao de clientes cadastrados 
	 */
	private static final long serialVersionUID = 6777827662866118885L;
	private JTable tableClientes;

	public ListaClintes() {
		
		addComponents();
	}

	private void addComponents() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		

		Vector<String> columnNames = new Vector<>();
		columnNames.add("Codigo");
		columnNames.add("Nome");
		
  		
		tableClientes = new JTable(null, columnNames);
		tableClientes.setFillsViewportHeight(true);
  		
		
		JScrollPane tableScroll = new JScrollPane(tableClientes);
		tableScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tableScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(tableScroll, gbc);
	}
}	
