package fr.ENI.HiddenFigures.Enchere.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class TestDALconnection {
	public static void main(String[] args) {
		//La façons de Stéphane
		
		/*
		 * String url =
		 * "jdbc:sqlserver://localhost:1433;databaseName=GESTIONS_NOTES_USERS"; String
		 * login = "sa"; String pwd = "Pa$$w0rd";
		 * 
		 * 
		 * try { Connection connexion = DriverManager.getConnection(url, login, pwd); }
		 * catch (SQLException e) { System.out.println("Connexion échouée"); }
		 * 
		 * System.out.println("Connexion réussie ");
		 */ 
		 		
		//La façons de Emmanuel
		try {
			Connection cnx = ConnectionProvider.getConnection();
			System.out.println("Connexion goooood");
		} catch (SQLException e) {
			System.err.println("Connexion échouée");
		}
	}

}
