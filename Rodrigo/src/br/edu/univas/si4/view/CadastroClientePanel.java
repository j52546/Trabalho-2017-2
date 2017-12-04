package br.edu.univas.si4.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.univas.si4.controller.ClienteController;
import br.edu.univas.si4.model.ClienteTO;

public class CadastroClientePanel extends JPanel{

	private static final long serialVersionUID = -5083485119067419418L;
	
	private ClienteController clienteController = new ClienteController();
	
	private JLabel labelNome;
	private JTextField textNome;
	private JLabel labelNascimento;
	private JFormattedTextField textNascimento;
	private JLabel labelSexo;
	private JRadioButton buttonMasculino;
	private JRadioButton buttonFeminino;
	private ButtonGroup buttonGroup;
	private JLabel labelCpf;
	private JFormattedTextField textCpf;
	private JLabel labelEndereco;
	private JTextField textRua;
	private JLabel labelBairro;
	private JTextField textBairro;
	private JLabel labelCidade;
	private JTextField textCidade;
	private JLabel labelTel;
	private JFormattedTextField textTel;
	private JLabel labelEmail;
	private JTextField textEmail;
	private JButton buttonCadastro;
	
	
	private GridBagConstraints buttonConstraints;
	private GridBagConstraints labelNomeConstraints;
	private GridBagConstraints textNomeConstraints;
	private GridBagConstraints labelNascimentoConstraints;
	private GridBagConstraints labelSexoConstraints;
	private GridBagConstraints masculinoConstraints;
	private GridBagConstraints femininoConstraints;
	private GridBagConstraints labelCpfConstraints;
	private GridBagConstraints textCpfConstraints;
	private GridBagConstraints textNascimentoConstraints;
	private GridBagConstraints labelRuaConstraints;
	private GridBagConstraints textRuaConstraints;
	private GridBagConstraints labelBairroConstraints;
	private GridBagConstraints textBairroConstraints;
	private GridBagConstraints labelCidadeConstraints;
	private GridBagConstraints textCidadeConstraints;
	private GridBagConstraints labelTelConstraints;
	private GridBagConstraints textTelConstraints;
	private GridBagConstraints labelEmailConstraints;
	private GridBagConstraints textEmailConstraints;
	
	private MaskFormatter mascaraData;
	private MaskFormatter mascaraCpf;
	private MaskFormatter mascaraRg;
	private MaskFormatter mascaraTel;
		
	public CadastroClientePanel(){
			initialize();
	}
	
	private void initialize(){
		setLayout(new GridBagLayout());
		getMascaraData();
		getMascaraCpf();
		getMascaraTel();
		
		this.add(getLabelNome(),getLabelNomeConstraints());
		this.add(getTextNome(),getTextNomeConstraints());
		this.add(getLabelNascimento(),getLabelNascimentoConstraints());
		this.add(getTextNascimento(),getTextNascimentoConstraints());
		this.add(getLabelCpf(),getLabelCpfConstraints());
		this.add(getTextCpf(),getTextCpfConstraints());
		this.add(getLabelRua(),getLabelEnderecoConstraints());
		this.add(getTextRua(),getTextEnderecoConstraints());
		this.add(getLabelBairro(),getLabelBairroConstraints());
		this.add(getTextBairro(),getTextBairroConstraints());
		this.add(getLabelCidade(),getLabelCidadeConstraints());
		this.add(getTextCidade(),getTextCidadeConstraints());
		this.add(getLabelTel(),getLabelTelConstraints());
		this.add(getTextTel(),getTextTelConstraints());
		this.add(getLabelEmail(),getLabelEmailConstraints());
		this.add(getTextEmail(),getTextEmailConstraints());
		this.add(getButtonCadastro(), getButtonConstraints());
		
	}
	
	private JButton getButtonCadastro() {
		if(buttonCadastro == null) {
			buttonCadastro = new JButton("Cadastrar");
			buttonCadastro.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					enviarDados();
					
				}
			});
		}
		
		return buttonCadastro;
	}
	
	private void enviarDados() {
		String nome = getTextNome().getText().toString();
		String cpf = getTextCpf().getText().toString();
		String rua = getTextRua().getText().toString();
		String cidade = getTextCidade().getText().toString();
		String telefone = getTextTel().getText().toString();
		String nascimento = getTextNascimento().getText().toString();
				
	
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date data = formato.parse(nascimento);
			ClienteTO cliente = new ClienteTO(nome, cpf, rua, cidade, telefone, data);
			this.clienteController.cadastrarCliente(cliente);
			
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "ERROR", "Error no formato da data", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		
	}
		
	private JLabel getLabelNome() {
		if(labelNome == null){
			labelNome = new JLabel();
			labelNome.setText("Nome");
		}
		return labelNome;
	}

	private JTextField getTextNome() {
		if(textNome == null){
			textNome = new JTextField();
			textNome.setColumns(10);
		}
		return textNome;
	}
	
	private JLabel getLabelNascimento() {
		if(labelNascimento== null){
			labelNascimento = new JLabel();
			labelNascimento.setText("Data de Nascimento");
		}
		return labelNascimento;
	}

	private JFormattedTextField getTextNascimento() {
		if(textNascimento == null){
			textNascimento = new JFormattedTextField(mascaraData);
			textNascimento.setPreferredSize(new Dimension(1, 20));
			
		}
		return textNascimento;
	}
	
	private MaskFormatter getMascaraData(){
		if(mascaraData == null){
			try{
			mascaraData = new MaskFormatter("##/##/####");
			mascaraData.setPlaceholderCharacter('_');
			}
			catch(ParseException excp){
				   System.err.println("Erro na formatação: " + excp.getMessage());
	               System.exit(-1);
			}
		}
		return mascaraData;
	}
	
	private JLabel getLabelSexo() {
		if(labelSexo == null){
			labelSexo = new JLabel();
			labelSexo.setText("Sexo");
		}
		return labelSexo;
	}
	


	private JLabel getLabelCpf() {
		if(labelCpf== null){
			labelCpf= new JLabel();
			labelCpf.setText("CPF");
		}
		return labelCpf;
	}

	private JFormattedTextField getTextCpf() {
		if(textCpf== null){
			textCpf= new JFormattedTextField(mascaraCpf);
			textCpf.setPreferredSize(new Dimension(1, 20));
		}
		return textCpf;
	}
	
	private MaskFormatter getMascaraCpf(){
		if(mascaraCpf == null){
			try{
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setPlaceholderCharacter('_');
			}
			catch(ParseException excp){
				   System.err.println("Erro na formatação: " + excp.getMessage());
	               System.exit(-1);
			}
		}
		return mascaraCpf;
	}
	
	private JLabel getLabelRua() {
		if(labelEndereco == null){
			labelEndereco = new JLabel();
			labelEndereco.setText("Rua");
		}
		return labelEndereco;
	}

	private JTextField getTextRua() {
		if(textRua == null){
			textRua = new JTextField();
			textRua.setColumns(10);
		}
		return textRua;
	}

	private JLabel getLabelBairro() {
		if(labelBairro == null){
			labelBairro = new JLabel();
			labelBairro.setText("Bairro");
		}
		return labelBairro;
	}

	private JTextField getTextBairro() {
		if(textBairro == null){
			textBairro = new JTextField();
			textBairro.setColumns(10);
		}
		return textBairro;
	}

	private JLabel getLabelCidade() {
		if(labelCidade== null){
			labelCidade = new JLabel();
			labelCidade.setText("Cidade");
		}
		return labelCidade;
	}

	private JTextField getTextCidade() {
		if(textCidade == null){
			textCidade = new JTextField();
			textCidade.setColumns(10);
		}
		return textCidade;
	}

	private JLabel getLabelTel() {
		if(labelTel== null){
			labelTel= new JLabel();
			labelTel.setText("Telefone");
		}
		return labelTel;
	}

	private JFormattedTextField getTextTel() {
		if(textTel == null){
			textTel= new JFormattedTextField(mascaraTel);
			textTel.setPreferredSize(new Dimension(1, 20));
		}
		return textTel;
	}
	
	private MaskFormatter getMascaraTel(){
		if(mascaraTel == null){
			try{
			mascaraTel = new MaskFormatter("(##)####-####");
			mascaraTel.setPlaceholderCharacter('_');
			}
			catch(ParseException excp){
				   System.err.println("Erro na formatação: " + excp.getMessage());
	               System.exit(-1);
			}
		}
		return mascaraTel;
	}

	private JLabel getLabelEmail() {
		if(labelEmail == null){
			labelEmail = new JLabel();
			labelEmail.setText("E-mail");
		}
		return labelEmail;
	}

	private JTextField getTextEmail() {
		if(textEmail == null){
			textEmail = new JTextField();
			textEmail.setColumns(10);
		}
		return textEmail;
	}
	
	private GridBagConstraints getButtonConstraints() {
		if(buttonConstraints == null) {
			buttonConstraints = new GridBagConstraints();
			buttonConstraints.gridx = 0;
			buttonConstraints.gridy = 10;
			buttonConstraints.gridwidth = 2;
			
		}
		
		return buttonConstraints;
	}
	
	private GridBagConstraints getLabelNomeConstraints() {
		if(labelNomeConstraints == null){
			labelNomeConstraints = new GridBagConstraints();
			labelNomeConstraints.gridx = 0;
			labelNomeConstraints.gridy = 0;
			labelNomeConstraints.insets = new Insets(15, 15, 15, 15);
		}
		return labelNomeConstraints;
	}

	private GridBagConstraints getTextNomeConstraints() {
		if(textNomeConstraints == null){
			textNomeConstraints = new GridBagConstraints();
			textNomeConstraints.gridx = 1;
			textNomeConstraints.gridy = 0;
			textNomeConstraints.ipadx = 100;
			textNomeConstraints.weightx = 1.0;
			textNomeConstraints.insets = new Insets(15, 15, 15, 15);
		}
		return textNomeConstraints;
	}

	private GridBagConstraints getLabelNascimentoConstraints() {
		if(labelNascimentoConstraints == null){
			labelNascimentoConstraints = new GridBagConstraints();
			labelNascimentoConstraints.gridx = 0;
			labelNascimentoConstraints.gridy = 1;
			labelNascimentoConstraints.insets = new Insets(15, 15, 15, 15);
		}
		return labelNascimentoConstraints;
	}

	private GridBagConstraints getTextNascimentoConstraints() {
		if(textNascimentoConstraints == null){
			textNascimentoConstraints = new GridBagConstraints();
			textNascimentoConstraints.gridx = 1;
			textNascimentoConstraints.gridy = 1;
			textNascimentoConstraints.ipadx = 100;
			textNascimentoConstraints.insets = new Insets(15, -95, 15, 15);
		}
		return textNascimentoConstraints;
	}

	private GridBagConstraints getLabelSexoConstraints() {
		if(labelSexoConstraints == null){
			labelSexoConstraints = new GridBagConstraints();
			labelSexoConstraints.gridx = 0;
			labelSexoConstraints.gridy = 2;
			labelSexoConstraints.insets = new Insets(15, 15, 15, 15);
		}
		return labelSexoConstraints;
	}


	private GridBagConstraints getLabelCpfConstraints() {
		if(labelCpfConstraints == null){
			labelCpfConstraints = new GridBagConstraints();
			labelCpfConstraints.gridx = 0;
			labelCpfConstraints.gridy = 4;
			labelCpfConstraints.insets = new Insets(15, 15, 15, 15);
 		}
		return labelCpfConstraints;
	}

	private GridBagConstraints getTextCpfConstraints() {
		if(textCpfConstraints == null){
			textCpfConstraints = new GridBagConstraints();	
			textCpfConstraints.gridx = 1;
			textCpfConstraints.gridy = 4;
			textCpfConstraints.ipadx = 100;
			textCpfConstraints.weightx = 1.0;
			textCpfConstraints.insets = new Insets(15, -95, 15, 15);
		}
		return textCpfConstraints;
	}

	private GridBagConstraints getLabelEnderecoConstraints() {
		if(labelRuaConstraints == null){
			labelRuaConstraints = new GridBagConstraints();
			labelRuaConstraints.gridx = 0;
			labelRuaConstraints.gridy = 5;
			labelRuaConstraints.insets = new Insets(15, 15, 15, 15);
		}
		return labelRuaConstraints;
	}

	private GridBagConstraints getTextEnderecoConstraints() {
		if(textRuaConstraints == null){
			textRuaConstraints = new GridBagConstraints();
			textRuaConstraints.gridx = 1;
			textRuaConstraints.gridy = 5;
			textRuaConstraints.ipadx = 100;
			textRuaConstraints.weightx = 1.0;
			textRuaConstraints.insets = new Insets(15, 15, 15, 15);
		}
		return textRuaConstraints;
	}

	private GridBagConstraints getLabelBairroConstraints() {
		if(labelBairroConstraints == null){
			labelBairroConstraints = new GridBagConstraints();
			labelBairroConstraints.gridx = 0;
			labelBairroConstraints.gridy = 6;
			labelBairroConstraints.insets = new Insets(15, 15, 15, 15);
		}
		return labelBairroConstraints;
	}

	private GridBagConstraints getTextBairroConstraints() {
		if(textBairroConstraints == null){
			textBairroConstraints = new GridBagConstraints();
			textBairroConstraints.gridx = 1;
			textBairroConstraints.gridy = 6;
			textBairroConstraints.ipadx = 100;
			textBairroConstraints.weightx = 1.0;
			textBairroConstraints.insets = new Insets(15, 15, 15, 15);
		}
		return textBairroConstraints;
	}

	private GridBagConstraints getLabelCidadeConstraints() {
		if(labelCidadeConstraints == null){
			labelCidadeConstraints = new GridBagConstraints();
			labelCidadeConstraints.gridx = 0;
			labelCidadeConstraints.gridy = 7;
			labelCidadeConstraints.insets = new Insets(15, 15, 15, 15);
		}
		return labelCidadeConstraints;
	}

	private GridBagConstraints getTextCidadeConstraints() {
		if(textCidadeConstraints == null){
			textCidadeConstraints = new GridBagConstraints();
			textCidadeConstraints.gridx = 1;
			textCidadeConstraints.gridy = 7;
			textCidadeConstraints.ipadx = 100;
			textCidadeConstraints.weightx = 1.0;
			textCidadeConstraints.insets = new Insets(15, 15, 15, 15);
		}
		return textCidadeConstraints;
	}

	private GridBagConstraints getLabelTelConstraints() {
		if(labelTelConstraints == null){
			labelTelConstraints = new GridBagConstraints();
			labelTelConstraints.gridx = 0;
			labelTelConstraints.gridy = 8;
			labelTelConstraints.insets = new Insets(15, 15, 15, 15);
		}
		return labelTelConstraints;
	}

	private GridBagConstraints getTextTelConstraints() {
		if(textTelConstraints == null){
			textTelConstraints = new GridBagConstraints();
			textTelConstraints.gridx = 1;
			textTelConstraints.gridy = 8;
			textTelConstraints.ipadx = 100;
			textTelConstraints.weightx = 1.0;
			textTelConstraints.insets = new Insets(15, -95, 15, 15);
		}
		return textTelConstraints;
	}

	private GridBagConstraints getLabelEmailConstraints() {
		if(labelEmailConstraints == null){
			labelEmailConstraints = new GridBagConstraints();
			labelEmailConstraints.gridx = 0;
			labelEmailConstraints.gridy = 9;
			labelEmailConstraints.insets = new Insets(15, 15, 15, 15);
		}
		return labelEmailConstraints;
	}

	private GridBagConstraints getTextEmailConstraints() {
		if(textEmailConstraints == null){
			textEmailConstraints = new GridBagConstraints();
			textEmailConstraints.gridx = 1;
			textEmailConstraints.gridy = 9;
			textEmailConstraints.ipadx = 100;
			textEmailConstraints.weightx = 1.0;
			textEmailConstraints.insets = new Insets(15, 15, 15, 15);
		}
		return textEmailConstraints;
	}

	
}
