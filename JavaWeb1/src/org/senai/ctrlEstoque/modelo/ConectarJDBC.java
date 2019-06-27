package org.senai.ctrlEstoque.modelo;

import java.sql.Connection;
import java.sql.DriverManager;



public class ConectarJDBC {
	
	public Connection getConectar() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/controle_estoque", "root", "");
			
		} catch (Exception e) {
			System.out.println("Erro de conexão");
			return null;
		}
	}
}
