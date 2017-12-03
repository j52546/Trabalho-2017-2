package br.edu.univas.si4.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	
	private static final long serialVersionUID = 5543282109511962957L;
	
	private String nameUser;

	public  MenuPanel(String name) {
		this.nameUser = name;
		addComponents();
	}

	private void addComponents() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(20, 20, 20, 20);
		
		JLabel label = new JLabel("Welcome to distribuidora Univas");
		label.setFont(new Font("SansSerif", Font.BOLD, 48));
		add(label, gbc);
		
		JLabel usuario = new JLabel("Logado com: " + this.nameUser);
		usuario.setFont(new Font("SansSerif", Font.BOLD, 26));
		gbc.gridy = 1;
		add(usuario, gbc);
	}

}
