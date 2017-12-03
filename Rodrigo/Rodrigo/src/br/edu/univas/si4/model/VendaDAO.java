package br.edu.univas.si4.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import javax.swing.JOptionPane;



public class VendaDAO {

	public boolean venda(String cpf, String codigo, int qtd, float preco) {
		Connection conn;
		
		String sql = "INSERT INTO venda(\r\n" + 
				"	 data_venda, codigo, cpf, venda_aprovada, quantidade, preco)\r\n" + 
				"	VALUES ( now(), ?, ?, ?, ?, ?);";
		try {
			conn = DBUtil.openConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(3, cpf);
			prep.setString(2,codigo);
			prep.setFloat(6, preco);	
			prep.setInt(5,qtd);
			prep.setBoolean(4, false);
				
				
			prep.executeUpdate();
			JOptionPane.showMessageDialog(null, "Venda realizada com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
		

		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
public ArrayList<VendaTO> getVendas() {
		Connection conn;
		String sql = "SELECT * FROM VENDA";
		ArrayList<VendaTO> vendas = new ArrayList<>();
		try {
			
			conn = DBUtil.openConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
			ResultSet result = prep.executeQuery();
			
			while(result.next()) {
				String nome = result.getString(7);
				String produto = result.getString(3);
				Integer qtd = result.getInt(5);
				Float valor = result.getFloat(6);
				Date data = result.getDate(2);
				VendaTO venda = new VendaTO(nome, produto, valor, qtd);
				vendas.add(venda);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	public void aprovarPedido(Integer id) {
		Connection conn;
		String sql = "UPDATE FROM venda SET venda_aprovada='true', WHERE id=?;";
		try {
			
			conn = DBUtil.openConnection();
				
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setLong(1, id);
			prep.execute();
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error ao Aprovar Pedido", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public ClienteTO findOneCPF(String cliente) {
		Connection conn;
		String sql = "SELECT * FROM CLIENTE WHERE CPF = ?";
		
		try {
			conn = DBUtil.openConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(1, cliente);
			
			ResultSet res = prep.executeQuery();
		
			while(res.next()) {
				return new ClienteTO(res.getString(2), res.getString(1), res.getString(3), res.getString(5), res.getString(6), res.getDate(4));
			}
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error ao acessar unico cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

}
