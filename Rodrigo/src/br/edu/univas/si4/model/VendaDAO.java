package br.edu.univas.si4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import br.edu.univas.si4.controller.ClienteController;
import br.edu.univas.si4.controller.ProdutoController;
import jdk.nashorn.internal.scripts.JO;

public class VendaDAO {
	
	private ClienteController clienteController; 
	private ProdutoController produtoController;
	
	public VendaDAO() {
		this.clienteController = new ClienteController();
		this.produtoController = new ProdutoController();
		
	}
	

	public ArrayList<VendaTO> searchFull(String dados, String opcao, Date data_inicio, Date data_final) {
		Connection conn;
		ClienteTO clienteTO = this.clienteController.findOne(dados);
		ProdutoTO produtoTO = this.produtoController.findOneName(dados);
		String sql = "SELECT * FROM VENDA WHERE "+ (opcao.equals("Cliente") ? "cpf" : "codigo") +" = ? AND DATA_VENDA BETWEEN ? AND ?";
		ArrayList<VendaTO> vendas = new ArrayList<>();
		
		try {
			conn = DBUtil.openConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
			System.out.println(sql);
			
			prep.setString(1, (opcao.equals("Cliente") ? clienteTO.getCpf() : produtoTO.getCodigo()));
			prep.setTimestamp(2, new Timestamp(data_inicio.getTime()));
			prep.setTimestamp(3, new Timestamp(data_final.getTime()));
			ResultSet res = prep.executeQuery();

			while(res.next()) {
				Date data = res.getDate(1);
				String cliente = res.getString(3);
				String produto = res.getString(2);
				boolean venda = res.getBoolean(4);
				int qtd = res.getInt(5);
				float preco = res.getFloat(6);
				int id = res.getInt(7);
				produtoTO = this.produtoController.findOne(produto);
				clienteTO = this.clienteController.findOneCPF(cliente);
			
				if(produtoTO != null && clienteTO != null) {
					System.out.println("OK");
					VendaTO vendaTO = new VendaTO(clienteTO.getNome(), produtoTO.getNome(), preco, qtd, venda);
					vendaTO.setId(id);
					vendaTO.setDataVenda(data);
					vendas.add(vendaTO);
				}
				
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,"Consulta não encontrada", "404 NOT FOUND", JOptionPane.DEFAULT_OPTION);
		}
		
		return vendas;
	}


	public ArrayList<VendaTO> getWithClientOrProduct(String dados, String opcao) {
		ArrayList<VendaTO> vendas = new ArrayList<>();
		Connection conn;
		boolean check = opcao.equalsIgnoreCase("Cliente") ? true : false;
		ProdutoTO produtoTO = null;
		ClienteTO clienteTO = null;
		if(check) {
			clienteTO = this.clienteController.findOne(dados);
		} else {
			produtoTO = this.produtoController.findOneName(dados);
		}
				
		String opcaoSQL = check ? "CPF" : "CODIGO";
		String sql = "SELECT * FROM VENDA WHERE "+ opcaoSQL +" = ? ";
	
		try {
			
			conn = DBUtil.openConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(1, check ? clienteTO.getCpf() : produtoTO.getCodigo());
			ResultSet res = prep.executeQuery();
			
			while(res.next()) {
				Date data = res.getDate(1);
				String cliente = res.getString(3);
				String produto = res.getString(2);
				boolean venda = res.getBoolean(4);
				int qtd = res.getInt(5);
				float preco = res.getFloat(6);
				int id = res.getInt(7);
				produtoTO = this.produtoController.findOne(produto);
				clienteTO = this.clienteController.findOneCPF(cliente);
			
				if(produtoTO != null && clienteTO != null) {
					VendaTO vendaTO = new VendaTO(clienteTO.getNome(), produtoTO.getNome(), preco, qtd, venda);
					vendaTO.setDataVenda(data);
					vendaTO.setId(7);
					vendas.add(vendaTO);
				}

				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Resultado não encontrado", "404 NOT FOUND", JOptionPane.DEFAULT_OPTION);
		}
	
		return vendas;
	
	}


	public ArrayList<VendaTO> getVendas() {
		ArrayList<VendaTO> vendas = new ArrayList<>();
		Connection conn;
				
		String sql = "SELECT * FROM VENDA";
	
		try {
			
			conn = DBUtil.openConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
		;
			ResultSet res = prep.executeQuery();
			while(res.next()) {
				Date data = res.getDate(1);
				String cliente = res.getString(3);
				String produto = res.getString(2);
				boolean venda = res.getBoolean(4);
				int qtd = res.getInt(5);
				float preco = res.getFloat(6);
				int id = res.getInt(7);
				ProdutoTO produtoTO = this.produtoController.findOne(produto);
				ClienteTO clienteTO = this.clienteController.findOneCPF(cliente);
			
				if(produtoTO != null && clienteTO != null) {
					VendaTO vendaTO = new VendaTO(clienteTO.getNome(), produtoTO.getNome(), preco, qtd, venda);
					vendaTO.setDataVenda(data);
					vendaTO.setId(id);
					vendas.add(vendaTO);
				}

				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Resultado não encontrado", "404 NOT FOUND", JOptionPane.DEFAULT_OPTION);
		}
	
		return vendas;
	}


	public void venda(String cpf, String codigo, int qtd, float preco) {
		Connection conn;
		
		String sql = "INSERT INTO venda(\r\n" + 
				"	 data_venda, codigo, cpf, venda_aprovada, quantidade, preco)\r\n" + 
				"	VALUES (now(), ?, ?, ?, ?, ?);";
		try {
			conn = DBUtil.openConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(2, cpf);
			prep.setString(1,codigo);
			prep.setFloat(5, preco);	
			prep.setInt(4,qtd);
			prep.setBoolean(3, false);
				
				
			prep.executeUpdate();
			JOptionPane.showMessageDialog(null, "Venda realizada com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
		

		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente", "Error", JOptionPane.ERROR_MESSAGE);
		
	}
	
}


	public ArrayList<VendaTO> getVendasFalse() {
		ArrayList<VendaTO> vendas = new ArrayList<>();
		Connection conn;
				
		String sql = "SELECT * FROM VENDA WHERE VENDA_APROVADA = FALSE";
	
		try {
			
			conn = DBUtil.openConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
		;
			ResultSet res = prep.executeQuery();
			while(res.next()) {
				Date data = res.getDate(1);
				String cliente = res.getString(3);
				String produto = res.getString(2);
				boolean venda = res.getBoolean(4);
				int qtd = res.getInt(5);
				float preco = res.getFloat(6);
				int id = res.getInt(7);
				ProdutoTO produtoTO = this.produtoController.findOne(produto);
				ClienteTO clienteTO = this.clienteController.findOneCPF(cliente);
			
				if(produtoTO != null && clienteTO != null) {
					VendaTO vendaTO = new VendaTO(clienteTO.getNome(), produtoTO.getNome(), preco, qtd, venda);
					vendaTO.setDataVenda(data);
					vendaTO.setId(id);
					vendas.add(vendaTO);
				}

				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Resultado não encontrado", "404 NOT FOUND", JOptionPane.DEFAULT_OPTION);
		}
	
		return vendas;
	}


	public void aprovarVenda(int id) {
		Connection conn;
		String sql = "UPDATE VENDA SET VENDA_APROVADA = TRUE WHERE ID = ?";
		
		try {
			
			conn = DBUtil.openConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setInt(1, id);
			
			prep.execute();
			JOptionPane.showMessageDialog(null, "Pedido Aprovado", "Pedido", JOptionPane.PLAIN_MESSAGE);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Falha ao aprovar venda", "ERROR", JOptionPane.ERROR_MESSAGE);
		}		
		
	}


	public ArrayList<VendaTO> getVendasAprovadoOrReprovado(String condition) {
		ArrayList<VendaTO> vendas = new ArrayList<>();
		Connection conn;
		boolean opcao = condition.equals("Aprovado") ? true : false;		
		String sql = "SELECT * FROM VENDA WHERE VENDA_APROVADA = ?";
	
		try {
			
			conn = DBUtil.openConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setBoolean(1, opcao);
			ResultSet res = prep.executeQuery();
			while(res.next()) {
				Date data = res.getDate(1);
				String cliente = res.getString(3);
				String produto = res.getString(2);
				boolean venda = res.getBoolean(4);
				int qtd = res.getInt(5);
				float preco = res.getFloat(6);
				int id = res.getInt(7);
				ProdutoTO produtoTO = this.produtoController.findOne(produto);
				ClienteTO clienteTO = this.clienteController.findOneCPF(cliente);
			
				if(produtoTO != null && clienteTO != null) {
					VendaTO vendaTO = new VendaTO(clienteTO.getNome(), produtoTO.getNome(), preco, qtd, venda);
					vendaTO.setDataVenda(data);
					vendaTO.setId(id);
					vendas.add(vendaTO);
				}

				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Resultado não encontrado", "404 NOT FOUND", JOptionPane.DEFAULT_OPTION);
		}
	
		return vendas;
	}

}
