package br.com.projetoFinal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.projetoFinal.pojo.Curso;

public class CursoDAO implements iCursoDAO {

	@Override
	public void cadastrar(Curso curso) throws SQLException{
		
		PreparedStatement comando = null;
		 
			try {
			Connection conexao = ConexaoRemota.conectar();
			String sql = "INSERT INTO curso(nome_curso,carga_horaria) VALUE (?,?)";
			comando = conexao.prepareStatement(sql);			
			comando.setString(1,curso.getNome());
			comando.setInt(2, curso.getCargaHoraria());
			comando.executeUpdate();
		
			
			}catch (Exception ex) {
			ex.printStackTrace();
			
			}finally {
				System.out.println("Mensagem ou funcionalidade padrão");
				Conexao.conectar().close();
				comando.close();
			}
		
	}

	@Override
	public void pesquisar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(Curso curso) {
		// TODO Auto-generated method stub
		PreparedStatement comando = null;
		try {
			Connection conexao = ConexaoRemota.conectar();
			String sql = "UPDATE curso SET nome_curso=?,carga_horaria=? WHERE id=?";//chupa hacker(lad.galinha) otário	
			comando = conexao.prepareStatement(sql);
			comando.setString(1,curso.getNome());
			comando.setInt(2,curso.getCargaHoraria());
			comando.setInt(3,curso.getId());
			comando.executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			System.out.println("mensagem ou funcionalidade padrão!");			
		}			
	}



	@Override
	public void excluir(int id) {
		// TODO Auto-generated method stub
		PreparedStatement comando = null;
		 
		try {
		Connection conexao = ConexaoRemota.conectar();
		String sql = "DELETE FROM curso WHERE id = ?";
		comando = conexao.prepareStatement(sql);			
		comando.setInt(1,id);
		comando.execute();
	
		
		}catch (Exception ex) {
		ex.printStackTrace();
		
		}finally {
			System.out.println("Mensagem ou funcionalidade padrão");
			
		}
		
	}
}

