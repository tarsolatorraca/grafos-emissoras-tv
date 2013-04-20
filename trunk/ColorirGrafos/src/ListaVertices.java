import java.util.*;

public class ListaVertices extends ArrayList<Vertice> {
    
	public ListaVertices() { }
    
    // Ordena a lista de vertices por ordem decrescente.
    public void ordena(){
        for(int a = 0 ; a < this.size() - 1; a ++)
            for(int b = a + 1; b < this.size(); b ++)
                if(this.get(a).getGrau() < this.get(b).getGrau())    
                    //troca
                    this.set(a, this.set(b,this.get(a))); // O set de um array list
                                                          // retorna o objecto da
                                                          // da posicao "setada" 
    }
}