package br.edu.univas.si4.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.sun.glass.events.MouseEvent;
import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import br.edu.univas.si4.controller.ClienteController;
import br.edu.univas.si4.model.ClienteTO;

public class ExcluirCliente extends JPanel {
	
	
	private ClienteController clienteController;
	private GridBagLayout gridBag;
	private JTable table;
	private JPanel topPanel;
	private JPanel centerPanel;
	private JPanel rightPanel;
	private ArrayList<ClienteTO> clientes;
	private String cpf;
	
	public ExcluirCliente() {	
		this.clienteController = new ClienteController();
		this.setLayout(new BorderLayout());
		initializeComponents();
	}
	
	public void initializeComponents() {
		this.gridBag = new GridBagLayout();
		this.centerPanel = new JPanel();
		this.topPanel = new JPanel();
		this.rightPanel = new JPanel();
		
		JLabel title = new JLabel("Excluir Cliente");
		title.setFont(new Font("Arial", Font.BOLD, 36));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		this.topPanel.add(title);
		
		this.add(this.topPanel, BorderLayout.NORTH);
		

		Vector<String> columnNames = new Vector<>();
		columnNames.add("CPF");
		columnNames.add("Nome");
		columnNames.add("Cidade");
		columnNames.add("Telefone");
  		
  		table = new JTable(null, columnNames);
  		table.setFillsViewportHeight(true);
  		this.getClientes();
  		
  				
		JScrollPane tableScroll = new JScrollPane(table);
		tableScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tableScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	
		this.add(tableScroll, BorderLayout.CENTER);
		
		JButton btnExlcuir = new JButton("Excluir");
		btnExlcuir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
				
			}
		});
		this.rightPanel.add(btnExlcuir);
		
		this.rightPanel.setPreferredSize(new Dimension(500, 0));
		this.add(this.rightPanel, BorderLayout.EAST);
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });		
	}
	
	  private void jTableMouseClicked(java.awt.event.MouseEvent evt) {                                     
	        
	        // get the model from the jtable
	       DefaultTableModel model = (DefaultTableModel)table.getModel();
	        int selectedRowIndex = table.getSelectedRow();
   	        
	       if(selectedRowIndex != -1) this.cpf = model.getValueAt(selectedRowIndex, 0).toString();
	  
	    }  
	
	public void getClientes() {
		this.clientes = this.clienteController.getClientes();
						
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);
								
					for (ClienteTO cliente : this.clientes) {
						dtm.addRow(new Object[] {
								cliente.getCpf(),
								cliente.getNome(),
								cliente.getCidade(),
								cliente.getTelefone()
						});
						
					}
						
			
	}
	
	private void excluirCliente() {
		this.clienteController.excluirCliente(this.cpf);
		this.getClientes();
	}
	

}
