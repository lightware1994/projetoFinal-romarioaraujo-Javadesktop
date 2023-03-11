package br.com.projetoFinal.gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.com.projetoFinal.dao.*;


//conexão
import java.sql.Connection;
//driver
import java.sql.DriverManager;

//buscar os dados
import java.sql.ResultSet;
//exceção
import java.sql.SQLException;
//comandos SQL (Prepar...)
import java.sql.Statement;

import javax.swing.JButton;
//Formulário
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//Barra de Rolagem
import javax.swing.JScrollPane;
//Tabela
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
//MVC - View (tabelinha) Model (Dados)
import javax.swing.table.DefaultTableModel;

import br.com.projetoFinal.dao.ConexaoRemota;
import br.com.projetoFinal.pojo.Curso;


//Classe criada por mim.
public class tabelaDeCursosGUI extends JFrame implements ActionListener{
	
	private JPanel painel1;
	private JPanel painel2;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtCargaHoraria;
	private JTable table;
	private int id=0;
	//POG -Prog. Orien. Gabm
	private String nome;
	private int cargaHoraria;
	private DefaultTableModel modelo;
	 
    public tabelaDeCursosGUI() throws SQLException {
    	
    	//instanciando os painéis
    	painel1 = new JPanel();
    	painel2 = new JPanel();
    	btnExcluir = new JButton("Excluir");
    	btnAlterar= new JButton("Alterar");
    	txtId = new JTextField(10);
    	txtNome = new JTextField("Digite o nome",60);
    	txtCargaHoraria = new JTextField("Digite a carga horária",40);
        txtId.setEnabled(false);
    	painel1.add(txtId);
    	painel1.add(txtNome);
    	painel1.add(txtCargaHoraria);
    	painel1.add(btnExcluir);
    	painel1.add(btnAlterar);
    	
    	
    	painel1.setLayout(new GridLayout(1,5));
    	
    	 BorderLayout border = new BorderLayout();
         setLayout(border);
         preencherTabela();
    
       
       //chamados dos eventos
       btnExcluir.addActionListener(this);
       btnAlterar.addActionListener(this);
	   add(painel1,BorderLayout.NORTH);
	   add(painel2,BorderLayout.WEST);
	   pack();
	   setVisible(true);    	
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnExcluir) {
		    new CursoDAO().excluir(id);
			
			for(int i=0 ; i<modelo.getRowCount(); i++){ 
				if(Integer.parseInt( modelo.getValueAt(i, 0).toString()) == id) {  
				
				
				//VERIFICA QUAL LINHA ESTÁ SELECIONADA PARA SELECIONAR OUTRA LINHA ANTES DE APAGAR -- APAGAR UMA LINHA SELECIONADA GERA UM ERRO 
				if(table.getSelectedRow() != 0) 
				{ 
				table.setRowSelectionInterval(0, 0); 
				}else { table.setRowSelectionInterval(1, 1); 
				}
				modelo.removeRow(i); 
			}
	}


	JOptionPane.showMessageDialog(null, "Dados 	Excluídos com Sucesso");

		}else if(e.getSource()==btnAlterar) {
			
			Curso curso = new Curso();
			curso.setNome(txtNome.getText());
			curso.setCargaHoraria(Integer.parseInt((txtCargaHoraria.getText())));
			curso.setId(Integer.parseInt(txtId.getText()));
			new CursoDAO().alterar(curso);
			for(int i=0 ; i<modelo.getRowCount(); i++) { 
				if(Integer.parseInt( modelo.getValueAt(i, 0).toString()) == Integer.parseInt(txtId.getText())) { 
					modelo.setValueAt(txtNome.getText(), i, 1); 
					modelo.setValueAt(Integer.parseInt(txtCargaHoraria.getText()), i, 2); 
			} 
		}
			txtNome.setText("");
			txtCargaHoraria.setText("");
			JOptionPane.showMessageDialog(null,"Alterado com sucesso");
	
		}
}
	//Refatorei o método..
	public void preencherTabela() throws SQLException {
		
	    //INICIO---------------A parte do Banco de dados 
    	Statement comando = null;
		Connection conexao = ConexaoRemota.conectar();
		String sql = "SELECT * FROM curso";
		comando = conexao.createStatement();
		ResultSet rs = comando.executeQuery(sql);
		//executar tudo que a gente a gente fez.
		// Criar modelo de tabela
		
		//Montar a DefaultTableModel
		String[] colunas = {"ID", "Nome do Curso", "CH"};
		modelo = new DefaultTableModel(colunas, 0);
		try {
		while (rs.next()) {
		    int id = rs.getInt("id");
		    String nome = rs.getString("nome");
		    int cargaHoraria = rs.getInt("cargaHoraria");
		    Object[] row = {id, nome, cargaHoraria};
		    modelo.addRow(row);
		}
		}catch(SQLException e){
			System.err.println("Ocorreu um erro: ");
			e.printStackTrace();
		}
       // Criar tabela
	   table = new JTable(modelo);
	   
	   // ajuste manual da largura das colunas
	   table.getColumnModel().getColumn(0).setPreferredWidth(3);
	   table.getColumnModel().getColumn(1).setPreferredWidth(350);
	   table.getColumnModel().getColumn(2).setPreferredWidth(50);
        

	   // desabilita o redimensionamento automático das colunas
	   table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
	   //trabalhar o evento de selecionar...
	   

	   table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent event) {
		        // verifica se o evento foi gerado pela seleção de uma linha
		        if (!event.getValueIsAdjusting()) {
		            // obtém o índice da linha selecionada
		            int rowIndex = table.getSelectedRow();

		            // obtém o valor da célula da coluna 0 da linha selecionada
		            Object cellValue = table.getValueAt(rowIndex,0);
		            Object cellValue1 = table.getValueAt(rowIndex,1);
		            Object cellValue2 = table.getValueAt(rowIndex,2);
                    id = Integer.parseInt(cellValue.toString());
                    nome = String.valueOf(cellValue1.toString());
                    cargaHoraria = Integer.parseInt(cellValue2.toString());
                    txtId.setText(String.valueOf(id));
                    txtNome.setText(nome);
                    txtCargaHoraria.setText(String.valueOf(cargaHoraria));
		            // exibe o valor da célula em um JOptionPane
		            //JOptionPane.showMessageDialog(null, "Valor selecionado: " + cellValue);
		        }
		    }
		});
	   table.revalidate();
	   table.repaint();
	   //twitch tv 
	   //1 erro
	   
	   //******************************************************************* 
	   
	   JScrollPane scrollPane = new JScrollPane(table);
       //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       painel2.add(scrollPane);	
		
	}
}
