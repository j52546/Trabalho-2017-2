package br.edu.univas.si4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ProdutoDAO {
	
	
	public ArrayList<ProdutoTO> findAll() {
		Connection conn;
		String sql = "SELECT * FROM PRODUTO";
		ArrayList<ProdutoTO> produtos = new ArrayList<>();
		
		try {
			
			conn = DBUtil.openConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
			ResultSet res = prep.executeQuery();
			while(res.next()) {
				produtos.add(new ProdutoTO(
						res.getString(1),
						res.getString(2),
						res.getFloat(3),
						res.getInt(4)
				));
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error ao consultar os produtos", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return produtos;
	}

	public ProdutoTO findOneName(String nome) {
		Connection conn;
		String sql = "SELECT * FROM PRODUTO WHERE NOME = ?";
		
		try {
			
			conn = DBUtil.openConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
			
			prep.setString(1, nome);
			ResultSet res = prep.executeQuery();
			while(res.next()) {
				return new ProdutoTO(res.getString(1), res.getString(2), res.getFloat(3), res.getInt(4));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error ao acessar unico produto", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		return null;
	}
}