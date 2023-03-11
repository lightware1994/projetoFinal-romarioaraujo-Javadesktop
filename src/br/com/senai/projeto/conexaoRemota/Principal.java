package br.com.senai.projeto.conexaoRemota;

import br.com.projetoFinal.dao.*;

public class Principal {

	public static void main(String[] args) {
		//new CursosGUI().setVisible(true);
		
		ConexaoRemota.conectar();
		ConexaoRemota.fecharConexao();
	}
	
	
}
