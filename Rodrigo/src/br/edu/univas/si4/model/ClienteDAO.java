package br.edu.univas.si4.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import javax.swing.JOptionPane;

public class ClienteDAO {

	public boolean cadastrarCliente(ClienteTO clientTO) {
		Connection conn;
		
		String sql = "INSERT INTO cliente (cpf_cnpj, nome, rua, nascimento, cidade, telefone) VALUES "
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
			JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "Sucesso", JOptionPane.OK_OPTION);
		

		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "ERROR", "Erro ao cadastrar cliente", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
	
}
