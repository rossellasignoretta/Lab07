package it.polito.tdp.dizionario.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordDAO {

	/*
	 * Ritorna tutte le parole di una data lunghezza che differiscono per un solo carattere
	 */
	/*public List<String> getAllSimilarWords(String parola, int numeroLettere) {
		
		Connection conn = DBConnect.getInstance().getConnection();
		String sql = "SELECT nome FROM parola WHERE LENGTH(nome) = ? AND nome LIKE ?";
		PreparedStatement st;

		try {

			st = conn.prepareStatement(sql);
			st.setInt(1, numeroLettere);

			List<String> parole = new ArrayList<String>();
			
			for (int i=0; i<parola.length(); i++){
				char [] array= parola.toCharArray();
				array[i]='_';
				String stemp= new String(array);

				st.setString(2, stemp);
				ResultSet res = st.executeQuery();
				while (res.next()){
					String nome=res.getString("nome");
					parole.add(nome);
				}				
			}
				
			return parole;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}*/

	/*
	 * Ritorna tutte le parole di una data lunghezza
	 */
	public List<String> getAllWordsFixedLength(int numeroLettere) {

		Connection conn = DBConnect.getInstance().getConnection();
		String sql = "SELECT nome FROM parola WHERE LENGTH(nome) = ?;";
		PreparedStatement st;

		try {

			st = conn.prepareStatement(sql);
			st.setInt(1, numeroLettere);
			ResultSet res = st.executeQuery();

			List<String> parole = new ArrayList<String>();

			while (res.next())
				parole.add(res.getString("nome"));

			return parole;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

}
