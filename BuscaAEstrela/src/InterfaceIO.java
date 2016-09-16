import javax.swing.JOptionPane;

public class InterfaceIO {
	
public void insereTabuleiro(){
	
	BuscaAStar busca = new BuscaAStar();
	int [][] tab = new int[3][3];
	JOptionPane.showMessageDialog(null, "Digite o tabuleiro: \n"
			+ "Valores de entrada permitidos: 1 a 8 \n"
			+ "Insira valor 0 para posição vazia\n");
	
	for(int l = 1;l <= 3; l++){
		for(int c = 1;c <= 3; c++){
			int valor = Integer.parseInt(JOptionPane.showInputDialog("Linha : "+l + " Coluna : "+c));
			if(valor < 0 || valor >=9){
				JOptionPane.showMessageDialog(null, "Valor Incorreto!");
				c--;
				if(valorJaExisteNoTabuleiro(valor, tab)){
					JOptionPane.showMessageDialog(null, "Valor Já Inserido!");
					c--;
				}
			}
			else{
				tab[l-1][c-1] = valor;
			}
		
		}
	}
	
	int qtdPecasFora = busca.getQtdPecasForaDoLugar(tab);
	int manDistance = busca.getManhattanDistance(tab);
	int qtCircular = busca.getQuantidadeTrocas(tab);
	System.out.println("Quantidade de pecas fora do lugar: "+ qtdPecasFora);
	System.out.println("Manhattan distance : "+ manDistance);
	System.out.println("Quantidade pecas invertidas : "+ qtCircular);;

	
	
	mostraTabuleiro(tab);
}

public boolean valorJaExisteNoTabuleiro(int valor, int tabuleiro[][]){
	for(int l = 0; l < 3; l++){
		for(int c = 0; c < 3; c++){
			if(tabuleiro[l][c] == valor){
				return true;
			}
		}
	}
	return false;
}

public void mostraTabuleiro(int [][] tabuleiro){
	String saida = "";
	for(int l = 0; l < 3; l++){
		for(int c = 0; c < 3; c++){
			
			if(c == 2)
				saida += tabuleiro[l][c]+ "\n";
			else
				saida += tabuleiro[l][c]+ "  ";
			
			
		}
	}
	JOptionPane.showMessageDialog(null,saida);
	
}


}
