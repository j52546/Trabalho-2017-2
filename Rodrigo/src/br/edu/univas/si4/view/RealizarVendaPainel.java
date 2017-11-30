package br.edu.univas.si4.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RealizarVendaPainel extends JPanel{

	private static final long serialVersionUID = -8758838927010719813L;
	public JPanel griBag = null;
	
	public RealizarVendaPainel() {
		
		this.setLayout(new BorderLayout());
		setPanel();
		addComponets();
		
	}
	
	private void setPanel() {
		this.griBag = new JPanel();
		this.griBag.setLayout(new BorderLayout());
	}


	private void addComponets() {
		
		JLabel label = new JLabel();
		label.setText("Vendas");
		label.setFont(new Font("SansSerif", Font.BOLD, 48));
		
		this.add(label, BorderLayout.NORTH);
		this.add(this.griBag, BorderLayout.CENTER);
		addInputs();
		
	}
	
	private void addInputs() {
		JLabel codigoProduto = new JLabel("Informe código do produto");
		JTextField campoProduto = new JTextField();
		JLabel codigoCliente = new JLabel("Informe codigo do cliente");
		JTextField campoCliente = new JTextField();
		
		this.griBag.add(codigoProduto, this.setGbc(0, 0));
		this.griBag.add(campoProduto, this.setGbc(1, 0));
		this.griBag.add(codigoCliente, this.setGbc(0, 1));
		this.griBag.add(campoCliente, this.setGbc(1, 1));
	}
	
	
	private GridBagConstraints setGbc(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = x;
		gbc.gridy = y;
			
		return gbc;
	}
	
	
	
	
	
}

