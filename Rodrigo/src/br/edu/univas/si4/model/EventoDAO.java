package br.edu.univas.si4.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class EventoDAO {

	public void insertNewEvento() throws DBException {

		String sentenca = "INSERT INTO EVENTO " + " (CODIGO, ENTRADA, SAIDA, DOCUMENTO)" + " VALUES (?, ?, ?, ?)";

		Date umaHoraAtras = new Date(System.currentTimeMillis() - (60 * 60 * 1000));

		Connection conn = null;
		try {
			conn = DBUtil.openConnection();
			conn.setAutoCommit(false);// o blog exige isso
			PreparedStatement prepStat = conn.prepareStatement(sentenca);
			prepStat.setInt(1, 2);

			prepStat.setTimestamp(2, new Timestamp(umaHoraAtras.getTime()));
			prepStat.setTimestamp(3, new Timestamp(System.currentTimeMillis()));// agora

			// blob - binary lob
			String message = "Testando 123 blob";
			byte[] bytes = message.getBytes();
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			prepStat.setBinaryStream(4, bais);

			prepStat.execute();
			conn.commit();
		} catch (Exception e) {
			throw new DBException("Erro salvando Evento ", e);
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public void consultaEvento(Date inicio, Date fim) throws DBException {

		//System.out.println(inicio + " - " + fim);
		
		String sql = "SELECT CODIGO, ENTRADA, SAIDA, DOCUMENTO " 
				   + " FROM EVENTO "
				   + " WHERE entrada > ? AND entrada < ? ";

		Connection conn = null;
		try {
			conn = DBUtil.openConnection();
			PreparedStatement prep = conn.prepareStatement(sql);
			
			//setar os par�metros
			prep.setTimestamp(1, new Timestamp(inicio.getTime()));
			prep.setTimestamp(2, new Timestamp(fim.getTime()));

			ResultSet rs = prep.executeQuery();
			while (rs.next()) {

				int codigo = rs.getInt(1);
				Date entrada = new Date(rs.getTimestamp(2).getTime());
				Date saida = new Date(rs.getTimestamp(3).getTime());

				InputStream documento = rs.getBinaryStream(4);

				System.out.println("codigo: " + codigo);
				System.out.println("entrada: " + entrada);
				System.out.println("saida: " + saida);

				byte[] bytes = new byte[30];
				try {
					documento.read(bytes);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("documento: " + new String(bytes));
			}
		} catch (SQLException e) {
			throw new DBException("Erro consultando evento", e);
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

}