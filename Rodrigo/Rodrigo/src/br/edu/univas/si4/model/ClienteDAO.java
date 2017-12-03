package br.edu.univas.si4.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ClienteDAO {

	public boolean cadastrarCliente(ClienteTO clientTO) {
		Connection conn;
		
		String sql = "INSERT INTO cliente (cpf, nome, rua, nascimento, cidade, telefone) VALUES "
				+ "(?,?,?,?,?,?)";
		
		try {
			conn = DBUtil.openConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(1, clientTO.getCpf());
			prep.setString(2, clientTO.getNome());
			prep.setString(3, clientTO.getRua());
			prep.setTimestamp(4, new Timestamp(clientTO.getNascimento().getTime()));
			prep.setString(5, clientTO.getCidade());
			prep.setString(6, clientTO.getTelefone());
				
			prep.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "Sucesso", JOptionPane.PLAIN_MESSAGE);
		

		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
	public ArrayList<ClienteTO> getClientes() {
		Connection conn;
		String sql = "SELECT * FROM CLIENTE";
		ArrayList<ClienteTO> clientes = new ArrayList<>();
		try {
			
			conn = DBUtil.openConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
			ResultSet result = prep.executeQuery();
			
			while(result.next()) {
				String nome = result.getString(2);
				String cpf = result.getString(1);
				String rua = result.getString(3);
				Date nascimento = result.getDate(4);
				String cidade = result.getString(5);
				String telefone = result.getString(6);
				ClienteTO cliente = new ClienteTO(nome,cpf,rua,cidade,telefone,nascimento);
				clientes.add(cliente);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return clientes;
	}
	
	public void deleteCliente(String cpf) {
		Connection conn;
		String sql = "DELETE FROM CLIENTE WHERE CPF = ?";
		try {
			
			conn = DBUtil.openConnection();
				
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(1, cpf);
			prep.execute();
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error ao excluir clientes", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//====================================
	
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
	
	//===========================
	
	
}
