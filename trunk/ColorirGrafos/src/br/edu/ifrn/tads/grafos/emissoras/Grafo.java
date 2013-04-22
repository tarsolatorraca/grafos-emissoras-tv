package br.edu.ifrn.tads.grafos.emissoras;

import java.io.*;
import java.util.*;

/**
 * Classe que representa o Grafo para a coloração.
 * 
 * @author Alessandro
 *
 */
public class Grafo {
    private ListaVertices    lst_vertices;
    private AdjacencyMatrix  adjacencias;
    private ListaVertices    red            = new ListaVertices(); 
    private ListaVertices    yellow         = new ListaVertices(); 
    private ListaVertices    green          = new ListaVertices(); 
    private ListaVertices    blue           = new ListaVertices(); 
    private Vertice          vertice_actual;
    private int[][]          pares          = new int[18][2];
    
    public Grafo() {
        setLstVertices(new ListaVertices());
        setAdjacencias(new AdjacencyMatrix());
        setRed(new ListaVertices());
        setYellow(new ListaVertices());
        setGreen(new ListaVertices());
        setBlue(new ListaVertices());
        // nao fiz o set do vertice_actual
    }
    
    public Grafo(AdjacencyMatrix adj){
        setAdjacencias(adj);
        setLstVertices(new ListaVertices());
        atribuiVertices();
        atribuiVizinhos();
        
        lst_vertices.ordena();
        lst_vertices.get(0).setCor(1);
        red.add(lst_vertices.get(0));
        
        resultado();
    }
    //--------------------------------------------------------------------------
    
   /**
    *   Percorrendo a lista de vertices do grafo, e consecutivamente
    *   percorrendo a lista dos vizinhos do vertice actual.
    *   Retorna o total de vizinhos do grafo.
    * @param l
    * @return
    */
    public int contaVizinhos(ListaVertices l){
        int total = 0;
        for(Vertice v : l){
            total += v.getVizinhos().size();
        }
        return total;
    }
    
    /**
     *  Verifica se determinado vertice esta contido na lista de cores usadas.
     * @param v o vértice do grafo
     * @param red a cor do vértice
     * @param yellow cor do vértice
     * @param green cor do vértice
     * @param blue cor do vértice
     * @return o número da cor do vértice
     */
    public int estaContido(Vertice v, ListaVertices red, ListaVertices yellow,
            ListaVertices green, ListaVertices blue){
        
        return (red.contains(v)) ? 0 :
            (yellow.contains(v)) ? 1 :
                (green.contains(v))  ? 2 :
                    (blue.contains(v))   ? 3 : -123456;
    }
    
    public void par(int contador, int a, int b){
        this.getPares()[contador][0] = a;
        this.getPares()[contador][1] = b;
        
    }
    /**
     * Retorna a lista dos primeiros pares do grafo.
     */
    public void primeiros_pares() {
        for (int a = 0; a < lst_vertices.get(0).getVizinhos().size(); a++) {
            this.getPares()[a][0] = lst_vertices.get(0).getLabel() + 1;
            this.getPares()[a][1] = lst_vertices.get(0).getVizinhos().get(a).getLabel() + 1;
        }
        
    }
    /**
     * Algoritmo que resolve o problema proposto.
     */
    //Das 4 cores
    public void resultado(){
        int[] cores_usadas =  new int[4];
        int balde = 0;
        int y = 0;
        int x = 0;
        int contador = lst_vertices.get(0).getVizinhos().size();
        
        cores_usadas[0] = 78;
        primeiros_pares();
        
        // percorre o vector de vertices
        for (int a = 1; a < lst_vertices.size(); a++) {
            // percorre o vector de vizinhos para esse vertice a.
            for (int b = 0; b < lst_vertices.get(a).getVizinhos().size(); b++) {
                if(lst_vertices.get(a).getVizinhos().get(b).getCor() == -1){
                    // Porque este vertice nao esta pintado, ele e parte 
                    // integrante da lista de pares do grafo.
                    par(contador ++,
                            lst_vertices.get(a).getLabel() + 1,
                            lst_vertices.get(a).getVizinhos().get(b).getLabel() + 1);
                }else{
                    // Porque este vertice esta pintado, verifica a sua cor e
                    // marca a cor usada pelo mesmo, no vector de cores.
                    balde = estaContido(lst_vertices.get(a).getVizinhos().get(b),
                            red,
                            yellow,
                            green,
                            blue);
                    cores_usadas[balde] = 78;
                }
            }// fim de percorrer vizinhos
            
            // Percorre o vector das cores usadas na procura do primeiro 
            // valor == 0 que significa que sera esta a cor a usar.
            for (int h = 0; h < cores_usadas.length; h++) {
                if(cores_usadas[h] == 0){
                    x = h;
                    h = cores_usadas.length;
                }
            }
            // Atribui entao a cor
            lst_vertices.get(a).setCor(x + 1);
            
            // passa o cores_usadas todo a zeros
            for (int i = 0; i < cores_usadas.length; i++) cores_usadas[i] = 0;
            
            // Serve para saber a que cor de tinta vamos juntar o vertice
            switch(x){
                case 0:
                    red.add(lst_vertices.get(a));
                    break;
                case 1:
                    yellow.add(lst_vertices.get(a));
                    break;
                case 2:
                    green.add(lst_vertices.get(a));
                    break;
                case 3:
                    blue.add(lst_vertices.get(a));
                    break;
                default:
                    System.out.println("foo");
            }
        }// fim de percorrer lista de vertices
    }
    
    /**
     * percorre a matriz de adjacencia e adiciona os seus vertices na lista do grafo.
     */
    public void atribuiVertices(){
        for (int i = 0; i < this.adjacencias.getLines(); i++) {
            // Vertice(label, grau)
            vertice_actual = new Vertice(i, this.adjacencias.sumRow(i));
            lst_vertices.add(vertice_actual);
        }
    }
    
    /**
     * percorre a matriz de adjacencia e adiciona os vizinhos ao vertices.
     */
    public void atribuiVizinhos(){
        for (int i = 0; i < this.adjacencias.getLines(); i++) {
            for (int j = 0; j < adjacencias.getColumns(); j++) {
                if(adjacencias.getMat()[i][j] == 1){
//                    lst_vertices.get(i).getVizinhos().add(
//                            new Vertice(j + 1, lst_vertices.get(j).getGrau()));
                    lst_vertices.get(i).getVizinhos().add(lst_vertices.get(j));
                    
                }
            }
        }
    }
    //--------------------------------------------------------------------------
    public ListaVertices getLstVertices() {
        return lst_vertices;
    }
    
    public void setLstVertices(ListaVertices Vertices) {
        this.lst_vertices = Vertices;
    }
    
    public AdjacencyMatrix getAdjacencias() {
        return adjacencias;
    }
    
    public void setAdjacencias(AdjacencyMatrix Adjacencias) {
        this.adjacencias = Adjacencias;
    }
    
    public ListaVertices getRed() {
        return red;
    }
    
    public void setRed(ListaVertices red) {
        this.red = red;
    }
    
    public ListaVertices getYellow() {
        return yellow;
    }
    
    public void setYellow(ListaVertices yellow) {
        this.yellow = yellow;
    }
    
    public ListaVertices getGreen() {
        return green;
    }
    
    public void setGreen(ListaVertices green) {
        this.green = green;
    }
    
    public ListaVertices getBlue() {
        return blue;
    }
    
    public void setBlue(ListaVertices blue) {
        this.blue = blue;
    }
    
    public Vertice getVertice_actual() {
        return vertice_actual;
    }
    
    public void setVertice_actual(Vertice vertice_actual) {
        this.vertice_actual = vertice_actual;
    }
    
    public int[][] getPares() {
        return pares;
    }
    
    public void setPares(int[][] pares) {
        this.pares = pares;
    }
    
    // Percorre a lista de pares do grafo e retorna uma string 
    // formatada em DOT.
    public String criaDot(){
        int pares = contaVizinhos(getLstVertices()) / 2;
        String s = "GRAFO{\n";
        
        for (int a = 0; a < pares; a++) {
            //for (int b = 0; b < 2; b++) {
            s += getPares()[a][0] + "--" +
                    getPares()[a][1] + ";\n";
            //}
        }
        
        for (int a = 0; a < getLstVertices().size(); a++) {
            switch(getLstVertices().get(a).getCor()){
                case 1:
                    s += (getLstVertices().get(a).getLabel() + 1 ) +
                            "[color=red];\n";
                    break;
                case 2:
                    s += (getLstVertices().get(a).getLabel() + 1 ) +
                            "[color=yellow];\n";
                    break;
                case 3:
                    s += (getLstVertices().get(a).getLabel() + 1 ) +
                            "[color=green];\n";
                    
                    break;
                case 4:
                    s += (getLstVertices().get(a).getLabel() + 1 ) +
                            "[color=blue];\n";
                    break;
                default:
                    //nada
            }
        }
        s += "}";
        return s;     
    }
    // Grava a lista de pares num ficheiro em format dot.
    public void gravaDot(String file, String dot){
        Formatter escritor = null;
        try {
            
            escritor = new Formatter(new File(file));
            
        }catch(FileNotFoundException fnfe){
            System.err.println("O Arquivo não foi encontrado!");
            System.exit(1);
        }
        
        escritor.format(dot);
        escritor.close();
    }
    
}