package br.com.projetoFinal.desktop;

import br.com.projetoFinal.dao.ConexaoRemota;
import br.com.projetoFinal.gui.cursoGUI;
import br.com.projetoFinal.pojo.Competencia;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Competencia c1 = new Competencia();
		Competencia c2 = new Competencia();
		Competencia c3 = new Competencia();
		Competencia c4 = new Competencia();
		
		//Estágio
		System.out.println("Totaal de objetos:" + Competencia.getContador());
		
		//vetor (matriz unidimensional)
		//conhecimento - habilidade - atitude - IA
		Object [] competencia = {"Conhecimento", "Habilidade", "Atitude", "IA"};
		
		System.out.println(competencia[0]);
		System.out.println(competencia[1]);
		System.out.println(competencia[2]);
		System.out.println(competencia[3]);
		
	//Chamando meu primeiro JFrame
	new cursoGUI().setVisible(true);
		//ConexaoRemota.conectar();
	}
}
