package br.edu.univas.si4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import jdk.nashorn.internal.scripts.JO;



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

	public ArrayList<FuncionarioTO> searchFuncionario(String dados, String opcao) {
		Connection conn;
		String sql = "SELECT * FROM FUNCIONARIO WHERE " + opcao + " LIKE ?";
		ArrayList<FuncionarioTO> funcionarios = new ArrayList<>();
		
		try {
			
			conn = DBUtil.openConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(1, dados);
			ResultSet result = prep.executeQuery();
			
			while(result.next()) {
				String cpf = result.getString(1);
				String cod = result.getString(2);
				String nome = result.getString(3);
				String rua = result.getString(4);
				String funcao = result.getString(5);
				String bairro = result.getString(6);
				String telefone = result.getString(10);
								
				FuncionarioTO funcionario = new FuncionarioTO(nome, funcao);
				funcionario.setBairro(bairro);
				funcionario.setCod(cod);
				funcionario.setCpf(cpf);
				funcionario.setRua(rua);
				funcionario.setTelefone(telefone);
				funcionarios.add(funcionario);
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error ao consultar funcionarios", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return funcionarios;
	}
	
	public ArrayList<FuncionarioTO> findAll() {
		Connection conn;
		String sql = "SELECT * FROM FUNCIONARIO";
		ArrayList<FuncionarioTO> funcionarios = new ArrayList<>();
		try {
			conn = DBUtil.openConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
			ResultSet result = prep.executeQuery();
			while(result.next()) {
				String cpf = result.getString(1);
				String cod = result.getString(2);
				String nome = result.getString(3);
				String rua = result.getString(4);
				String funcao = result.getString(5);
				String bairro = result.getString(6);
				String telefone = result.getString(10);
								
				FuncionarioTO funcionario = new FuncionarioTO(nome, funcao);
				funcionario.setBairro(bairro);
				funcionario.setCod(cod);
				funcionario.setCpf(cpf);
				funcionario.setRua(rua);
				funcionario.setTelefone(telefone);
				funcionarios.add(funcionario);
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error ao trazer funcionarios", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		return funcionarios;
	}

}
