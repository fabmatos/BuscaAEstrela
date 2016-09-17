import java.awt.List;
import java.util.ArrayList;
import java.util.Stack;

public class BuscaAStar {
	
	protected Nodo nodo;
	protected int qtdEstados; //Quantidade total de estados percorridos ate ordenacao
	protected int qtdNodosFronteira; //Maior quantidade de nodos da fronteira
	protected ArrayList<Nodo> visitados = new ArrayList<Nodo>();//Lista de nodos já visitados
	protected ArrayList<Nodo> fronteira = new ArrayList<Nodo>();//Lista de nodos da fronteira
	protected Stack<Nodo> caminho = new Stack<Nodo>();//Pilha de nodos para armazenar o caminho da solução do tabuleiro
	protected final int [][] objetivo = {{1,2,3},{4,5,6},{7,8,0}};//Nodo objetivo
	
	
	public Stack<Nodo> resolver(Nodo nodo){
		
		return this.caminho;
	}
	//Retorna o valor da f(n) = g(n)+h(n)
	public int getFuncao(Nodo node){
		node.setProfundidade(1);
		return this.getCusto(node)+this.getHeuristica(node.getTab());
	}
	//Cálculo do custo até determinado estado do tabuleiro
	public int getCusto(Nodo nodo){
		return nodo.getProfundidade();
	}
	//Cálculo das 3 heurísticas(Soma)
	public int getHeuristica(int[][]tabuleiro){
		return this.getQtdPecasForaDoLugar(tabuleiro)+
				this.getManhattanDistance(tabuleiro)+
				this.getQuantidadeTrocas(tabuleiro);
	}
	//Heuristica 1 : Contador da quantidade de peças fora do lugar
	public int getQtdPecasForaDoLugar(int [][] tabuleiro){
		int [][] objetivo = new int[3][3];
		objetivo = this.getNodoObjetivo();
		int qtdPecas = 0;
		for(int l = 0; l < tabuleiro.length; l++){
			for(int c = 0; c < tabuleiro.length; c++){
				if(tabuleiro[l][c] != objetivo[l][c])
					qtdPecas++;
			}
		}
		return qtdPecas;
	}
	//Heuristica 2 : Soma das distâncias que cada peça está da sua posição origem
	public int getManhattanDistance(int [][]tabuleiro){
		int soma = 0;//Soma das distancias dos numeros fora do lugar
		for(int numero = 0; numero < 9; numero ++){
			int [] coordenadas = new int [2];
			int [] coordenadasObjetivo = new int [2];
			coordenadas = this.getPosicaoDoValor(numero, tabuleiro);
			coordenadasObjetivo = this.getPosicaoFinalDoValor(numero);
			
			int linha = coordenadas[0];
			int coluna = coordenadas[1];
			int linhaFinal = coordenadasObjetivo[0];
			int colunaFinal = coordenadasObjetivo[1];
			
			soma += Math.abs(linha - linhaFinal) + Math.abs(coluna - colunaFinal);
			
		}
		return soma;
		
	}
	//Heuristica 3: Quantidade de duplas (*2) de pecas que estao em situação de inversão direta
	public int getQuantidadeTrocas(int [][]tabuleiro){
		
		int quantDuplas = 0;
		int posAtual [] = new int[2];
		int [] posObjetivo = new int[2];
		
		for(int valor = 1; valor < 9; valor++){
			if(valorEstaEmPosicaoInversaoDireta(valor,tabuleiro)){
				
				posAtual = this.getPosicaoDoValor(valor, tabuleiro); 
				posObjetivo = this.getPosicaoFinalDoValor(valor);
				
				int lOrigN = posAtual[0];
				int cOrigN = posAtual[1];
				int nDest = objetivo[lOrigN][cOrigN];
				int lDestN = posObjetivo[0];
				int cDestN = posObjetivo[1];
				int nOrigN = tabuleiro[lDestN][cDestN];
				
				if(nDest == nOrigN)
					quantDuplas++;
			}
		}
		return quantDuplas;
		
	}
	//Retorna o nodo objetivo
	public int [][] getNodoObjetivo(){
		return this.objetivo;
	}
	//Retorna true se o nodo for nodo objetivo
	public boolean ehNodoObjetivo(int [][] nodo){
		int qtdIguais = 0;//conta quantidade de valores nodo = objetivo
		for(int l = 0; l < nodo.length; l++){
			for(int c = 0; c < nodo.length; c++){
				if(nodo[l][c] == objetivo[l][c])
					qtdIguais++;
			}
		}
		return qtdIguais == 9;
	}
	//Retorna a linha e a coluna de um valor
	public int [] getPosicaoDoValor(int valor, int [][] tabuleiro){
		int [] posicao = new int [2];
		for(int l = 0; l < tabuleiro.length; l++){
			for(int c = 0; c < tabuleiro.length; c++){
				if(tabuleiro[l][c] == valor){
					posicao[0] = l;
					posicao[1] = c;
				}
			}
		
		}
		return posicao;
	}
	//Retorna a linha e a coluna de cada valor de posicao no tabuleiro objetivo
	public int [] getPosicaoFinalDoValor(int valor){
		int [] posicao = new int [2];
		int [][] objetivo = new int[3][3];
		objetivo = this.getNodoObjetivo();
		for(int l = 0; l < objetivo.length; l++){
			for(int c = 0; c < objetivo.length; c++){
				if(objetivo[l][c] == valor){
					posicao[0] = l;
					posicao[1] = c;
				}
			}
		
		}
		return posicao;
		
	}
	//Retorna true se o valor esta em posicao de inversao direta
	public boolean valorEstaEmPosicaoInversaoDireta(int valor, int[][]tabuleiro){
		
		int [] coordenadas = new int [2];
		coordenadas = this.getPosicaoDoValor(valor, tabuleiro);
		int l = coordenadas[0];System.out.println(l);
		int c = coordenadas[1];System.out.println(c);
		
		if(valor==1){
			if((l==0 && c==1) || (l==1 && c==0))
				return true;
		}
		if(valor==2){
			if((l==0 && c==0) || (l==0 && c==2)|| (l==1 && c==1))
				return true;
		}
		if(valor==3){
			if((l==0 && c==1) || (l==1 && c==2))
				return true;
		}
		if(valor==4){
			if((l==0 && c==0) || (l==2 && c==0)|| (l==1 && c==1))
				return true;
		}
		if(valor==5){
			if((l==0 && c==1) || (l==1 && c==0)|| (l==2 && c==1) || (l==1 && c==2))
				return true;
		}
		if(valor==6){
			if((l==0 && c==2) || (l==1 && c==1))
				return true;
		}
		if(valor==7){
			if((l==1 && c==0) || (l==2 && c==1))
				return true;
		}
		if(valor==8){
			if((l==1 && c==1) || (l==2 && c==0))
				return true;
		}
	return false;
		
	}
	
}
