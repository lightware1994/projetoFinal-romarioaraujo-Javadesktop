package br.com.projetoFinal.dao;

import java.sql.SQLException;

import br.com.projetoFinal.pojo.Curso;

public interface iCursoDAO {
	
	//Apenas definir os contratos
	//Métodos abstraros(CRUD - Cadastrar , Pesquisar, Alterar, Excluir) 
	public abstract void cadastrar(Curso curso) throws SQLException;
	public abstract void pesquisar();
	public abstract void alterar(Curso curso);
	public abstract void excluir(int id);

	

}
