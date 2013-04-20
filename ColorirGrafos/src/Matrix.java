import java.io.*;
import java.util.*;
import javax.swing.*;
public class Matrix {
    private int lines;
    private int columns;
    private int mat[][] = null;
    
    public Matrix(){
        setArray(0,0);
    }
    
    public Matrix(int lines, int columns) {
        setArray(lines,columns);
    }
    
    public Matrix(int[][] mat, int lines, int columns){
        setArray(lines,columns);
        for (int a = 0; a < lines; a++)
            for (int b = 0; b < columns; b++)
                this.mat[a][b] = mat[a][b];
    }
    
    // this method is to be enhanced!
    //I must verify the parameters lines and columns
    public Matrix(int[] list, int lines, int columns){
        int counter  = 0;
        setArray(lines, columns);
        for (int a = 0; a < lines; a++)
            for (int b = 0; b < columns; b++){
            this.mat[a][b] = list[counter];
            counter ++;
            }
    }
    
    public void setAllTo(int x){
        for (int a = 0; a < lines; a ++)
            for (int b = 0; b < columns; b++)
                this.setElement(a,b,x);
    }
    
    public boolean symmetrical(){
        int a = 1, b = 0;
        boolean sym = true;
        if(this.lines != this.columns)
            return false;
        else
            while (sym && a < getColumns()) { // throught lines
            b = 0;
            while (sym && b < getLines()) { // throught columns
                sym = this.mat[a][b] == this.mat[b][a]; // set sym with the
                b ++;                                   // match's result
            }
            a ++;
            }
        return sym;
    }// EO symmetrical
    
    public int sumRow(int m){
        int sum = 0;
        for(int a = 0 ; a < getColumns(); a ++)
            sum += this.mat[m][a];
        return sum;
    }
    
    public String toString(){
        String s = "";
        for (int a = 0; a < this.getLines(); a++){
            for (int b = 0; b < this.getColumns(); b++)
                s += " " + this.mat[a][b];
            s += "\n";
        }
        return s;
    }
    
    public void setArray(int lines, int columns){
        this.lines   = lines;
        this.columns = columns;
        this.setMat(new int[lines][columns]);
    }
    
    
    public void printMatrix(){
        String l = "";
        for (int a = 0; a < this.getLines(); a ++){
            l = "";
            for (int b = 0; b < this.getColumns(); b ++)
                l += this.getElement(a,b) + " ";
            System.out.println(l);
        }
    }
    
    public int getLines() {
        return lines;
    }
    
    public void setLines(int lines) {
        this.lines = lines;
    }
    
    public int getColumns() {
        return columns;
    }
    
    public void setColumns(int columns) {
        this.columns = columns;
    }
    
    public int getElement(int line, int column){
        return this.mat[line][column];
    }
    
    public void setElement(int line, int column, int x){
        this.mat[line][column] = x;
    }
    
    public int[][] getMat() {
        return mat;
    }
    
    public void setMat(int[][] mat) {
        this.mat = mat;
    }
    
    // Set the array to another give by parameter.
    public void attribution(int[][] mat){
        this.mat = mat;
    }
    
    public Matrix clone(){
        return new Matrix(mat, lines, columns);
    }
    
    public boolean nullDiagonal(){
        for (int a = 0; a < lines; a ++)
            if(mat[a][a] != 0)
                return false;
        return true;
    }
    
    //------------------------------------------------------------------
    // Este metodo tem de ser alterado.
    // Pretende-se que este metodo leia de um ficheiro e que
    // atribua aos campos:
    // lines
    // columns
    // mat
    // os valores que leu devidamente tratados.
    /*
    public void carrega_matriz(){
        int[][] m = {
            {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 1, 1, 0, 0, 0, 1, 1, 1},
            {1, 1, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 1, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 1, 1, 0, 1, 1},
            {0, 1, 0, 1, 1, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1, 0, 0}
        };
        this.setLines(10); // esta linha obrigatoria
        this.setColumns(10); // e esta tb
        
        this.setMat(m);
    }
    */
    public void carreg(){
        int      linha        = 0;
        int[][]  mat          = null;
        String   linha_actual = "";
        String[] palavras     = new String[100];
        Scanner  leitor       = null;
        
        try {
            JFileChooser f1 = new JFileChooser();
            f1.setDialogTitle("Indique o ficheiro matriz:");
            f1.showOpenDialog(null);
            
            leitor =  new Scanner( new FileReader(f1.getSelectedFile()));
            
            while(leitor.hasNextLine()){
                linha_actual = leitor.nextLine();
                
                palavras     = linha_actual.split("#");
                
                if(mat == null)
                    mat = new int[palavras.length][palavras.length];
//                
//                //Para limpar os espacos
//                for (int a = 0; a < palavras.length; a++) {
//                    palavras[a] = palavras[a].trim();
//                }
                
                for (int a = 0; a < palavras.length; a++){
                    
                    mat[linha][a] = Integer.parseInt(palavras[a].trim());
                }
                linha ++;
            }
            
        } catch (IOException IOE) {
            IOE.printStackTrace();
        }
        
        this.setLines(mat.length);
        this.setColumns(mat.length);
        this.setMat(mat);
    }//EO method
    
    public boolean isSquare(){
        return (this.lines == this.columns) ? true : false ;
    }
    
}//EO class()