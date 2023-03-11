package br.com.projetoFinal.dao;

import java.sql.Connection;
import java.sql.DriverManager; //é o cara!
import java.sql.SQLException;

public class ConexaoRemota {

	
	private static final String URL = "jdbc:mysql://root:806srWxdBHIVfcW8qLUz@containers-us-west-115.railway.app:7336/railway";
    private static Connection conn = null;
 	
    public static Connection conectar() {
        Connection conn = null;
        
        try { //tente conectar.
            conn = DriverManager.getConnection(URL);
            System.out.println("Conexão realizada com sucesso!");
            
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        } 
       
        return conn;
    }
    public static void fecharConexao() {
		try {
			conn.close();
			System.out.println("Conexao fechada com sucesso");
		} catch (SQLException e) {
			System.err.println("ERRO AO FECHAR CONEXAO:" + e.getMessage());
			e.printStackTrace();
		}
	}

}
    

