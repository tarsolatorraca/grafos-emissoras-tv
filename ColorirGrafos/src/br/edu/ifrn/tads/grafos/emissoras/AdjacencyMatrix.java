package br.edu.ifrn.tads.grafos.emissoras;


/**
 * Classe que representa a matriz de adjacência.
 * 
 * @author Alessandro
 *
 */

public class AdjacencyMatrix extends Matrix{
    private int[] degree;
    private int[] vertex;
    private int[] sorted_degree;
    private int[] sorted_vertex;
    
    public AdjacencyMatrix() {
        super();
        this.degree = computeDegree();
        for (int a = 0; a < super.getLines(); a++) this.vertex[a] = a;
        this.sorted_degree = new int[super.getLines()];
        this.sorted_vertex = new int[super.getLines()];
    }
    
    public AdjacencyMatrix(Matrix matrix){
        super(matrix.getMat(),matrix.getLines(),matrix.getColumns());
        this.degree = computeDegree();
        this.vertex = computeVertex();
        this.sorted_degree = new int[super.getLines()];
        this.sorted_vertex = new int[super.getLines()];
        sort();        
        this.degree = computeDegree();
        this.vertex = computeVertex();
        // Chamar este modo duas vezes foi um recurso. Porque se o 
        // chamar uma sez ele alem de me ordenar o sort_vertex_degree 
        // tambem me ordenava vertex_degree.    
        
    }
    //--------------------------------------------------------------------------
    public int[] getDegree() { return degree;}

    public void setDegree(int[] degree) { this.degree = degree;}

    public int[] getVertex() { return vertex;}

    public void setVertex(int[] vertex) { this.vertex = vertex;}

    public int[] getSorted_degree() { return sorted_degree;}

    public void setSorted_degree( int[] sorted_degree) { 
        this.sorted_degree = sorted_degree;
    }

    public int[] getSorted_vertex() { return sorted_vertex;}

    public void setSorted_vertex( int[] sorted_vertex) {
        this.sorted_vertex = sorted_vertex;
    }
    //--------------------------------------------------------------------------
    public String printSortedDegree(){
        String s = "";
        for (int a = 0; a < sorted_degree.length; a ++) {
            s += sorted_degree[a] + " ";
        }
        return s;
    }
    
    public String printSortedVertex(){
        String s = "";
        for (int a = 0; a < sorted_vertex.length; a ++) {
            s += sorted_vertex[a] + " ";
        }
        return s;
    }
    
    public String printDegree(){
        String s = "";
        for (int a = 0; a < degree.length; a ++) {
            s += degree[a] + " ";
        }
        return s;
    }

    public String printVertex(){
        String s = "";
        for (int a = 0; a < vertex.length; a ++) {
            s += vertex[a] + " ";
        }
        return s;
    }
    
    public int[] computeDegree(){
        int[] v = new int[super.getLines()];
        for (int a = 0; a < super.getLines(); a ++) {
            v[a] = super.sumRow(a);
        }
        return v;
    }
    
    public int[] computeVertex(){
        int[] v = new int[super.getLines()];
        for (int a = 0; a < super.getLines(); a ++) {
            v[a] = a;
        }
        return v;
    }  
    // Este método ordena os dois vectores vertex e degree.
    public void sort(){
        int tempD = 0;
        int tempV = 0;
        this.setSorted_degree(this.getDegree());
        this.setSorted_vertex(this.getVertex());
        for (int a = 0; a < sorted_degree.length - 1; a++) 
            for (int b = a + 1; b < sorted_degree.length; b++) 
                if(sorted_degree[b] > sorted_degree[a]){
                    tempD = sorted_degree[a];
                    tempV = sorted_vertex[a];
                    sorted_degree[a] = sorted_degree[b];
                    sorted_vertex[a] = sorted_vertex[b];
                    sorted_degree[b] = tempD;
                    sorted_vertex[b] = tempV;
                }
    }
}