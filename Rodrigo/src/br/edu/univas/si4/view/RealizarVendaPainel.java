package br.edu.univas.si4.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RealizarVendaPainel extends JFrame{

	
	private static final long serialVersionUID = -8758838927010719813L;
	
	private JPanel northPanel;
	private JPanel soulPanel;
	
	public RealizarVendaPainel() {
		initialize();
	}

	private void initialize() {
		
		setLayout(new BorderLayout());
		
		northPanel = new JPanel();
		northPanel.setBackground(Color.red);
		northPanel.setLayout(new GridLayout());
		
		
		
		soulPanel = new JPanel();
		soulPanel.setBackground(Color.GREEN);
		soulPanel.setLayout(new GridLayout());
		
		
	}
	

}
