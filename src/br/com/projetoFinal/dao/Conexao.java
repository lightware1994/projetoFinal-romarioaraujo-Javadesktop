package br.com.projetoFinal.dao;

import java.sql.Connection;
import java.sql.DriverManager; //é o cara!
import java.sql.SQLException;
public class Conexao {
	//Três variáveis
	//static --> 
	/*
	 * carro     moto     caminhão   onibus
	 * 
	 * 
	 */
	private static final String URL = "jdbc:mysql://localhost:3306/projetofinal";
    private static final String USER = "root";
    private static final String PASSWORD = "#SenaiJava209";	
    
    //final - constante.
    
    public static Connection conectar() {
        Connection conn = null;
        
        try { //tente conectar.
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão realizada com sucesso!");
            
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        } 
       
        return conn;
    }

}