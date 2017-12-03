package br.edu.univas.si4.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.edu.univas.si4.controller.FuncionarioController;
import br.edu.univas.si4.controller.VendaController;
import br.edu.univas.si4.model.VendaTO;


public class RelatorioVenda extends JPanel {

	private JPanel topPanel;
	private JPanel gridBag;
	private JTextField dados;
	private JComboBox<String> comboBox;
	private JTable tableClientes;
	private JTextField dataInicio;
	private JTextField dataFinal;
	private MaskFormatter mascara;
	private VendaController vendaController;
	private JLabel resultado;
	private JLabel resultadoEntrar;
	
	
	
	public RelatorioVenda() {
		this.vendaController = new VendaController();
		this.setLayout(new BorderLayout());
		initializeComponents();
	}
	
	public void initializeComponents() {
		this.setTopPanel();
		this.setCenterPanel();
		
	}
	
	public void setTopPanel() {
		this.topPanel = new JPanel();
		JLabel title = new JLabel("Relatorio de Vendas");
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
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 20, 5, 0);
		this.gridBag.add(information, gbc);
		
		JLabel datas = new JLabel("Filtar por data de inicio e data final (obs* tem que informar nome cliente ou produto)");
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		this.gridBag.add(datas, gbc);
		
		gbc.gridwidth = 1;
		dados = new JTextField();
		dados.setText("Informe o Nome do cliente aqui");
		dados.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				dados.setText("");
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gridBag.add(dados, gbc);
		
		this.comboBox = new JComboBox();
		this.comboBox.addItem("Cliente");
		this.comboBox.addItem("Produto");
		this.comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String opcao = comboBox.getSelectedItem().toString();
				dados.setText("Informe o "+opcao+" aqui");
				
			}
		});
		gbc.gridx = 2;
		gbc.gridy = 1;
		this.gridBag.add(this.comboBox, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 1;

		MaskFormatter mascara;
		try {
			this.mascara = new MaskFormatter("##/##/####");
			this.mascara.setPlaceholderCharacter('-');
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		dataInicio = new JFormattedTextField(this.mascara);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		
		this.gridBag.add(this.dataInicio, gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 1;
		
		
		dataFinal = new JFormattedTextField(this.mascara);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		this.gridBag.add(this.dataFinal, gbc);
		
		gbc.gridx = 5;
		gbc.gridy = 1;
		gbc.weightx = 0;
		
		JButton search = new JButton("Buscar");
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchCliente();
				
			}
		});
		this.gridBag.add(search, gbc);
		
		
		Vector<String> columnNames = new Vector<>();
		columnNames.add("Nome");
		columnNames.add("Produto");
		columnNames.add("Data de venda");
		columnNames.add("Quantidade");
		columnNames.add("Valor do pedido");
		columnNames.add("Aprovado");
		
		
		
  		
		tableClientes = new JTable(null, columnNames);
		tableClientes.setFillsViewportHeight(true);
  		
		
		JScrollPane tableScroll = new JScrollPane(tableClientes);
		
		tableScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		tableScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 1.0;
		gbc.gridwidth = 7;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(40, 40, 40, 40);
		gbc.anchor = GridBagConstraints.CENTER;
		this.gridBag.add(tableScroll, gbc);
		
		
		resultado = new JLabel("Valor das vendas ja aprovadas: ");
		gbc.gridy = 2;
		gbc.gridx = 1;
		gbc.insets = new Insets(15, 15, 15, 15);
		this.gridBag.add(resultado, gbc);
		
		resultadoEntrar = new JLabel("Valores a entrar ao serem aprovadas: ");
		gbc.gridx = 4;
		this.gridBag.add(resultadoEntrar, gbc);
		
		JComboBox<String> aprovado = new JComboBox<>();
		aprovado.addItem("Aprovado");
		aprovado.addItem("Nao Aprovado");
		aprovado.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getAprovadoOrReprovado(aprovado.getSelectedItem().toString());
			}

			
		});
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		
		this.gridBag.add(aprovado, gbc);
		
		this.add(this.gridBag, BorderLayout.CENTER);
	}
	
	public void searchCliente() {
		String dados = this.dados.getText().toString();
		String opcao = this.comboBox.getSelectedItem().toString();
		SimpleDateFormat format = new SimpleDateFormat("dd/mm/YYYY");
		
		
	
		if(!this.dataInicio.getText().equals("--/--/----") && !this.dataFinal.getText().equals("--/--/----")){
			try {
				Date data_inicio = format.parse(this.dataInicio.getText());
				Date data_final = format.parse(this.dataFinal.getText());
				ArrayList<VendaTO> vendasTO = this.vendaController.searchFull(dados, opcao, data_inicio, data_final);
				atualizarCampoResultado(vendasTO);
				atualizarTabela(vendasTO);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}} else {
			ArrayList<VendaTO> vendasTO = this.vendaController.getWithClientOrProduct(dados, opcao);
			atualizarCampoResultado(vendasTO);
			atualizarTabela(vendasTO);
		}
		
			
	}
	
	public void atualizarCampoResultado(ArrayList<VendaTO> vendas) {
		float soma = 0;
		float somaEntrar = 0;
			
		for(VendaTO venda : vendas) {
			if(venda.isAprovado()) {
				soma += venda.getQuantidade() * venda.getValorPedido();
			} else {
				somaEntrar += venda.getQuantidade() * venda.getValorPedido();
				
			}
		}
		
		this.resultado.setText("Valor das vendas ja aprovadas: R$ " + soma);
		this.resultadoEntrar.setText("Valores a entrar ao serem aprovadas: R$ " + somaEntrar);
		
		
	}
	
	public void atualizarTabela(ArrayList<VendaTO> dados) {
		       
   		DefaultTableModel dtm = (DefaultTableModel) tableClientes.getModel();
        dtm.setRowCount(0);
        
        for (VendaTO venda : dados) {
                 dtm.addRow(new Object[] {
                   venda.getNomeCliente(),
                   venda.getNomeProduto(),
                   venda.getDataVenda(),
                   venda.getQuantidade(),
                   venda.getValorPedido(),
                   venda.isAprovado()
            });
            
        }
	}
	
	public void getVendas() {
		ArrayList<VendaTO> vendaTO = this.vendaController.getVendas();
		atualizarCampoResultado(vendaTO);
		atualizarTabela(vendaTO);
	}
	
	private void getAprovadoOrReprovado(String string) {
		ArrayList<VendaTO> vendas = this.vendaController.getVendasAprovadoOrReprovado(string);
		this.atualizarTabela(vendas);
		this.atualizarCampoResultado(vendas);
		
	}

}
