import java.util.Scanner;


public class Coloracao {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Digite a quantidade de cidades que o projeto irá abranger: ");
		int qtdCidades = scan.nextInt();
		//Cidade.idCidade = 0;
		for(int i = 1; i <= qtdCidades; i++){
			System.out.println("Digite o nome da "+ i +"ª cidade: ");
			Cidade.nomeCidade = scan.nextLine();
			Cidade.idCidade ++;
		}
		System.out.println(Cidade.idCidade + ": "+ Cidade.nomeCidade);
//		funcao Maior_primeiro(G:grafo): Grafo colorido
//		i := 1;
//		while(G contem vertices nao coloridos){
//			for(cada vertice v de G nao colorido){
//				if(nenhum vertice adjacente a v possui a cor i){
//					vertice v = cor i;
//				}
//			}
//			i++;
//		}
//		return G;
		
	}

}
