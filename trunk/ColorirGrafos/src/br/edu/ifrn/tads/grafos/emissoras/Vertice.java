package br.edu.ifrn.tads.grafos.emissoras;

/**
 * Classe que representa os vértice do Grafo
 * @author Alessandro
 *
 */
public class Vertice {
   
	private int label;
    private int grau_entrada;
    private int grau_saida;
    private int grau;
    private int cor;
    private ListaVertices vizinhos;
    
    public Vertice() {
        setLabel(999);
        setGrau_entrada(-1);
        setGrau_saida(-1);
        setCor(-1);
        vizinhos = new ListaVertices();
        
    }
    
    public Vertice(int label, int ge, int gs, int cor){
        setLabel(label);
        setGrau_entrada(ge);
        setGrau_saida(gs);
        setCor(cor);
        vizinhos = new ListaVertices();
    }
    
    public Vertice(int label, int grau){
        setLabel(label);
        setGrau(grau);
        setCor(-1);
        vizinhos = new ListaVertices();
    }
    
    //--------------------------------------------------------------------------
    public int getGrau_entrada() {
        return grau_entrada;
    }
    
    public void setGrau_entrada(int grau_entrada) {
        this.grau_entrada = grau_entrada;
    }
    
    public int getGrau_saida() {
        return grau_saida;
    }
    
    public void setGrau_saida(int grau_saida) {
        this.grau_saida = grau_saida;
    }
    
    public int getCor() {
        return cor;
    }
    
    public void setCor(int cor) {
        this.cor = cor;
    }
    
    public ListaVertices getVizinhos() {
        return vizinhos;
    }
    
    public void setVizinhos(ListaVertices vizinhos) {
        this.vizinhos = vizinhos;
    }
    
    public int getGrau() {
        return grau;
    }
    
    public void setGrau(int grau) {
        this.grau = grau;
    }
    
    public int getLabel() {
        return label;
    }
    
    public void setLabel(int label) {
        this.label = label;
    }
    
    public void addVizinho(Vertice vizinho){
        this.vizinhos.add(vizinho);
    }
    
    public Vertice getVizinho(int pos){   
        return this.vizinhos.get(pos);
    }
    //--------------------------------------------------------------------------
    /**
     * Sobrescrita do método toString para mostrar os dados do Grafo.
     */
    @Override
    public String toString(){
        String s = "";
        
        for (int a = 0; a < vizinhos.size(); a++)
            if(a == 0)
                s += (vizinhos.get(a).getLabel() + 1) + ",";
            else
                if(a == vizinhos.size() - 1)
                    s += " " + (vizinhos.get(a).getLabel() + 1);
                else
                    s += " " + (vizinhos.get(a).getLabel() + 1) + ",";
            
        return "Vertice->" + (getLabel() + 1) + " Grau->" + getGrau() + " Vizinhos->{" + s + "} Cor->" + getCor();
    }
 
}