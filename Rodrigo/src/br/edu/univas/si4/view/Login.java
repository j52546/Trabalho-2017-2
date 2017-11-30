package br.edu.univas.si4.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.edu.univas.si4.controller.MainController;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MainController mainController;
	private JTextField usuarioText;
	private JPasswordField senhaText;
	private JPanel panel;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	
	public Login(MainController mainController) {
		this.mainController = mainController;
		this.setTitle("Login");
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setSize(300, 300);
		this.setResizable(false);
		setPanel();
		addComponents();
	}
	
	public void setPanel() {
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 3, 0, 3));
		panel.setLayout(new BorderLayout());
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridBagLayout());
		
		this.setContentPane(panel);
		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
	}
	
	private void addComponents() {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(10, 5, 0, 5);
		
		centerPanel.add(addJLabelField("Usuario"), gbc);
		usuarioText = this.addJTextField();
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		
		
		centerPanel.add(usuarioText, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		
		centerPanel.add(addJLabelField("Senha"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		
		
		senhaText = this.addPassword();
		centerPanel.add(senhaText, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 0, 120, 10);
		gbc.anchor = GridBagConstraints.LINE_START;
		
		bottomPanel.add(addButtonLogin("Login"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		gbc.anchor = GridBagConstraints.CENTER;
		bottomPanel.add(addButtonCancel("Cancelar"), gbc);
		
	}
	
	private JButton addButtonLogin(String title) {
		JButton button = new JButton(title);
		button.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				verificarUsuario();			
				
			}
		});
		
		return button;
		
	}
	
	public void verificarUsuario() {
		String username = this.usuarioText.getText();
		String password = new String(this.senhaText.getPassword());
		this.mainController.authUser(username, password);
	}
	
	private JButton addButtonCancel(String title) {
		JButton button = new JButton(title);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		return button;
	}
	
	private JLabel addJLabelField(String title) {
		JLabel label = new JLabel(title);
		return label;
	}
	
	private JTextField addJTextField() {
		JTextField text = new JTextField();
		text.setPreferredSize(new Dimension(200, 20));
		return text;
	}
	
	private JPasswordField addPassword() {
		JPasswordField password = new JPasswordField();
		password.setPreferredSize(new Dimension(200, 20));
		return password;
	}
	

}
