package br.edu.univas.si4.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import br.edu.univas.si4.controller.ClienteController;
import br.edu.univas.si4.controller.VendaController;
import br.edu.univas.si4.model.ClienteTO;
import br.edu.univas.si4.model.VendaTO;

public class AprovarVenda extends JPanel {
	

	private ClienteController clienteController;
	private VendaController vendaController;
	private GridBagLayout gridBag;
	private JTable table;
	private JPanel topPanel;
	private JPanel centerPanel;
	private JPanel rightPanel;
	private ArrayList<VendaTO> vendas;
	private Integer id;
	
	public AprovarVenda() {
		
		this.vendaController = new VendaController();
		this.setLayout(new BorderLayout());
		initializeComponents();
		this.getVendas();
		
	}
	
	public void initializeComponents() {
		this.gridBag = new GridBagLayout();
		this.centerPanel = new JPanel();
		this.topPanel = new JPanel();
		this.rightPanel = new JPanel();
		
		JLabel title = new JLabel("Aprovar Pedido");
		title.setFont(new Font("Arial", Font.BOLD, 36));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		this.topPanel.add(title);
		
		this.add(this.topPanel, BorderLayout.NORTH);
		
		Vector<String> columnNames = new Vector<>();
		columnNames.add("Nome Cliente");
		columnNames.add("Nome Produto");
		columnNames.add("Quantidade");
		columnNames.add("Valor");
  		
  		table = new JTable(null, columnNames);
  		table.setFillsViewportHeight(true);
  				
		JScrollPane tableScroll = new JScrollPane(table);
		tableScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tableScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	
		this.add(tableScroll, BorderLayout.CENTER);
		
		JButton aproPedido = new JButton("Aprovar Pedido");
		aproPedido.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				aprovarPedido();
				
			}
		});
		this.rightPanel.add(aproPedido);
		
		this.rightPanel.setPreferredSize(new Dimension(500, 0));
		this.add(this.rightPanel, BorderLayout.EAST);
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });		
	}	
	 private void jTableMouseClicked(java.awt.event.MouseEvent evt) {                                     
	        
	     
	       DefaultTableModel model = (DefaultTableModel)table.getModel();
	        int selectedRowIndex = table.getSelectedRow();
	        
	       if(selectedRowIndex != -1) this.id = (Integer) model.getValueAt(selectedRowIndex, 0);
	  
	    }  
	 
	 public void getVendas() {
			this.vendas = this.vendaController.getVendas();
							
					DefaultTableModel dtm = (DefaultTableModel) table.getModel();
					dtm.setRowCount(0);
									
						for (VendaTO venda : this.vendas) {
//							dtm.addRow(new Object[] {
//									venda.getNomeCliente().toString(),
//									venda.getNomeProduto().toString(),
//									venda.getQuantidade(),
//									venda.getValorPedido()
//							});
//							
						}
							
				
}
	 
	 private void aprovarPedido(){
		 this.vendaController.AprovarVenda(this.id);
		 this.getVendas();
		 
		}
	
}
