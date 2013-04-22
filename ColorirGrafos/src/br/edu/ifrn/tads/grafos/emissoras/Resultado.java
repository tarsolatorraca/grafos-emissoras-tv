package br.edu.ifrn.tads.grafos.emissoras;

import javax.swing.JOptionPane;

/**
 * Classe principal do Projeto.
 * 
 * @author Alessandro
 *
 */
public class Resultado {
    
	public Resultado() {}
	
    public static void main(String[] args){
        AdjacencyMatrix adj = null;
        Matrix matrix_bruta = new Matrix(20,20);
        
        //carrega os dados da matriz no ficheiro
        matrix_bruta.carreg();
        
        ///verifica se as linhas são iguais as colunas
        if(matrix_bruta.isSquare())
        	//verifica se a diagonal da matriz é 0
            if(matrix_bruta.nullDiagonal()){
            	//cria uma nova matriz de Adjacência
                adj = new AdjacencyMatrix(matrix_bruta);
                //cria um novo grafo da matriz de adjacência
                Grafo g = new Grafo(adj);
                ///grava no arquivo o grafo criado.
                g.gravaDot("result.dot", g.criaDot());
                JOptionPane.showMessageDialog(null, "Grafo criado.");
                
                System.out.println("EMISSORAS DE TELEVISÃO:\n");
                System.out.println("Vermelho: "+ g.getRed());
                System.out.println("Amarelo: "+ g.getYellow());
                System.out.println("Verde: "+ g.getGreen());
                System.out.println("Azul: "+ g.getBlue());
                
            }
    }
}