package br.edu.univas.si4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;



public class FuncionarioDAO {
	
	public void getAll(String[] args) throws DBException {
		String sql = "SELECT * FROM FUNCIONARIO";
		
		Connection conn = null;
		try {
			conn = DBUtil.openConnection();
			
			PreparedStatement prep = conn.prepareStatement(sql);
			
			ResultSet result = prep.executeQuery();
			while(result.next()) {
				System.out.println(result.getString(3));
			}
			
		} catch(Exception e ) {
			throw new DBException("Erro ao consultar BD ", e);
		}
	}
	
	public FuncionarioTO authenticateUser(String username, String password) throws DBException {
		
		String sql = "SELECT * FROM funcionario WHERE nome = ? AND senha = ?";
		Connection conn;
		try {
			conn = DBUtil.openConnection();
			PreparedStatement prepStat = conn.prepareStatement(sql);
			
			prepStat.setString(1, username);
			prepStat.setString(2, password);
			
			ResultSet res = prepStat.executeQuery();
			if(!res.next()) {
				throw new DBException();
			} else {
				FuncionarioTO f = new FuncionarioTO(res.getString(3), res.getString(5));
				return f;
			}
			
		} catch(Exception e) {
			throw new DBException("Usuario/senha inexistente");
		}
	}
	


}
