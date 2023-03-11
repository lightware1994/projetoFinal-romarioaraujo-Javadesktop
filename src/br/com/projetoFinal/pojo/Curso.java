package br.com.projetoFinal.pojo;

public class Curso {
	private int id;
	private String nome;
	private int cargaHoraria;
	
	public Curso() {}
	public Curso(String nome, int cargaHoraria) {
		super();
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
