package br.com.projetoFinal.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.projetoFinal.dao.CursoDAO;
import br.com.projetoFinal.pojo.Curso;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.FlowLayout;
import java.awt.GridLayout;

//import java.awt.event.ActionListener;//Resp.Técni: Sistema Operacional

//Porque senão? haja memória RAM, haja SSD

//ActionListener é uma interface
//Casado
/*
* public class Romulo implements Casamento{
*     //sobrescrita - polimorfismo 
*     @Override 
*     public void irPraCasa(){
*     
*     } 
* }
* 
* public class Romulo implements Mamae{
*      @Override
*      public void naoBeber( ){
*          if(beber == true)
*             System.out.println("Eu te Mato!");
*      }
* }
* 
* ActionListener é uma interface
* 
* Interface: é um recurso que possui métodos abstratos que obrigam
* as classes a implementar métodos (CONTRATO)
*/

public class cursoGUI extends JFrame implements ActionListener {
		// TODO Auto-generated method stub
		private JButton btnCadastrar;
		private JButton btnPesquisar;
		private JButton btnAlterar;
		private JButton btnExcluir;
		
		private JPanel pnlBotoes;
		private JPanel pnlComponentes;
		
		private JTextField txtCurso;
		private JTextField  txtCargaHoraria;
		
		private JLabel lblCurso;
		private JLabel lblTempoMin;
	
		
		public cursoGUI() {
		//Tratat o evento do botão
		btnCadastrar = new JButton("Cadastrar");
		btnPesquisar = new JButton("Pesquisar");
		btnAlterar = new JButton("Alterar");
		btnExcluir = new JButton("Excluir");
		
		//Atribuindo o botão ao container (JFrame)
		getContentPane().add(btnCadastrar);
		getContentPane().add(btnPesquisar);
		getContentPane().add(btnAlterar);
		getContentPane().add(btnExcluir);
		
		setLayout(new BorderLayout(1,4));
		
		setSize(800,600);
		pnlBotoes = new JPanel();
		pnlBotoes.setLayout(new GridLayout(1,4));
		
		btnCadastrar.addActionListener(this);
		btnPesquisar.addActionListener(this);
		btnAlterar.addActionListener(this);
		btnExcluir.addActionListener(this);
		
		
		pnlBotoes.add(btnCadastrar);
		pnlBotoes.add(btnPesquisar);
		pnlBotoes.add(btnAlterar);
		pnlBotoes.add(btnExcluir);
		
			
		pnlComponentes = new JPanel();
		//pnlComponentes.setBackground();
		pnlComponentes.setLayout(new FlowLayout());
		txtCurso = new JTextField(40);
		txtCargaHoraria = new JTextField(20);
		lblCurso = new JLabel("Curso:");
		lblTempoMin = new JLabel("Tempo Min..");
		pnlComponentes.add(lblCurso);
		pnlComponentes.add(txtCurso);
		pnlComponentes.add(lblTempoMin);
		pnlComponentes.add(txtCargaHoraria);
		
	
		
		getContentPane().add(pnlBotoes,BorderLayout.SOUTH);
		getContentPane().add(pnlComponentes,BorderLayout.WEST);
		
		
	}
		//tratar eventos (handler - ouvintes)
		@Override
		public void actionPerformed(ActionEvent e) {	
			if(e.getSource() == btnCadastrar) {
				
				//PreparedStatement (contra SQL INJECTION)
				//pegar os dados do usuário
			
				try {
					if((txtCurso.getText().equals(""))&& txtCargaHoraria.getText().equals("")){
						JOptionPane.showMessageDialog(null,"Campos obrigatórios!");
						
					}else{
						String nome = txtCurso.getText();
						int cargaHoraria = Integer.parseInt(txtCargaHoraria.getText());
						new CursoDAO().cadastrar(new Curso(nome,cargaHoraria));
						JOptionPane.showMessageDialog(null,"uhhu cadastrou");
						txtCurso.setText("");
						txtCargaHoraria.setText("");				
					}		
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"Alguma falha na conexão");
				}finally {
					
				}
			}else if(e.getSource() == btnPesquisar) {
				JOptionPane.showMessageDialog(null,"uhhu pesquisou");
				try {
					new tabelaDeCursosGUI().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				};
				//dever de casa SQL select
			}else if(e.getSource() == btnAlterar) {
				JOptionPane.showMessageDialog(null,"uhhu alterou");
				//dever de casa SQL alterar update
			}else {
				JOptionPane.showMessageDialog(null,"uhhu excluiu");
				//SQL delete
			}
			
		}

	}
		//tratar eventos (handler - ouvintes)
		
	/*	@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnCadastrar){
				
				
				//PreparedStatement (contra SQL INJECTION)
				//pegar os dados do usuário
				
				try {
					if(txtCurso.getText().equals("") && txtCargaHoraria.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campos obrigatório");
					}else {	
					String nome = txtCurso.getText();
					int cargaHoraria = Integer.parseInt(txtCargaHoraria.getText());
					new CursoDAO().cadastrar(new Curso(nome,cargaHoraria));
					JOptionPane.showMessageDialog(null,"uhhu cadastrou");
					txtCurso.setText("");
					txtCargaHoraria.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"Alguma falha na conexão");
					
				}
		public void actionPerformed(ActionEvent e) {	
			if(e.getSource() == btnCadastrar) {
				
				//PreparedStatement (contra SQL INJECTION)
				//pegar os dados do usuário
			
				try {
					if((txtCurso.getText().equals(""))&& txtCargaHoraria.getText().equals("")) {
						JOptionPane.showMessageDialog(null,"Campos obrigatórios");
						
					}else {
						String nome = txtCurso.getText();
						int cargaHoraria = Integer.parseInt(txtCargaHoraria.getText());
						new CursoDAO().cadastrar(new Curso(nome,cargaHoraria));
						JOptionPane.showMessageDialog(null,"uhhu cadastrou");
						txtCurso.setText("");
						txtCargaHoraria.setText("");				
					}		
					
			}else if(e.getSource() == btnPesquisar)	{
				JOptionPane.showMessageDialog(null, "Pesquisando"); // Faltou atenção na hora colocar o código(try e catch) na pesquisa.
			}else if(e.getSource() == btnAlterar) {
				JOptionPane.showMessageDialog(null, "Alterando");
			}else {
				JOptionPane.showMessageDialog(null, "Excluiu");
				}
			}
		}*/

