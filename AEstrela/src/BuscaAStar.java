import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JOptionPane;

public class BuscaAStar {
	
	protected Nodo nodo;
	protected int qtdEstados; //Quantidade total de estados percorridos ate ordenacao
	protected int qtdNodosFronteira; //Maior quantidade de nodos da fronteira
	protected ArrayList<Nodo> visitados = new ArrayList<Nodo>();//Lista de nodos já visitados
	protected ArrayList<Nodo> fronteira = new ArrayList<Nodo>();//Lista de nodos da fronteira
	protected Queue<Nodo> caminho = new LinkedList<Nodo>();//Fila de nodos para armazenar o caminho da solução do tabuleiro
	protected final int [][] objetivo = {{1,2,3},{4,5,6},{7,8,0}};//Nodo objetivo
	
	
	public void resolver(Nodo nodo){
		this.qtdEstados = 0;
		this.qtdNodosFronteira = 0;
		this.fronteira.add(nodo);
		
		if(this.ehNodoObjetivo(nodo.getTab())){
			
			System.out.println("Tamanho máximo de nodos na fronteira: "+this.qtdNodosFronteira+ "\n\n");
			this.mostraCaminho(nodo);
			
		}else{
			int heuristica = this.getHeuristica(nodo.getTab());
			nodo.setCustoTotal(heuristica);
			ArrayList<Nodo> filhos = this.expandirFilhos(nodo);
			this.adicionaNaFronteira(filhos);
			this.removeDaFronteira(nodo);
			nodo = this.getNext();
			if(nodo != null){
				this.resolver(nodo);
			}else{
				System.out.println("Sem solução!");
			}
		}
	}
	
	public Nodo getNext() {
        if(!fronteira.isEmpty()){
        		return Collections.min(fronteira);
        }else{
        		return null;
        }
    }
	//Empilha o caminho do nodo final até o inicial
	public void mostraCaminho(Nodo nodoFim){
		
		boolean isFinal = false;//Variavel de controle para se chegar ate o nodo inicial
		Nodo paiTemp;//Nodo para armazenar o nodo pai de forma temporaria
		Stack <Nodo> caminho = new Stack<Nodo>();//Pilha contendo o caminho da solução
		
		while(!isFinal){//Empilha do nodo objetivo até o nodo inicial
			caminho.push(nodoFim);
			if(nodoFim.getNodoPai() != null){
				paiTemp = nodoFim.getNodoPai();
				nodoFim = paiTemp;
			}
			else{
				isFinal = true;
			}
		}
		
		System.out.println("Quantidade de níveis até o objetivo: "+(caminho.size()-1)+"\n\n");//Mostra a quantidade de níveis descidos
		for(int i = 0; i < caminho.size();i++){
			paiTemp = caminho.pop();
			this.mostraTabuleiro(paiTemp.getTab());
		}
		
		
	}
	//Adiciona os filhos na fronteira caso ja nao tenham sido expandidos
	public void adicionaNaFronteira(ArrayList<Nodo> nodosFilhos){
		for(Nodo nodo : nodosFilhos){
			if(!this.visitados.contains(nodo.getIdNodo())){//Verifica se o nodo já não foi visitado
				this.fronteira.add(nodo);
			}
		}
		if(this.fronteira.size() > this.qtdNodosFronteira){//Verificação feita para setar o maior tamanho de elementos da fronteira
			this.qtdNodosFronteira = fronteira.size();
		}
	}
	//Remove um nodo da fronteira e o adiciona na lista de visitados
	public void removeDaFronteira(Nodo aRemover){
		
		this.visitados.add(aRemover);//Adiciona em visitados
		this.fronteira.remove(aRemover);//Remove da fronteira
		
	}
	//Expande os filhos de um nodo
	public ArrayList<Nodo> expandirFilhos(Nodo nodoPai){
		
		ArrayList<Nodo> filhos = new ArrayList<Nodo>();//Lista de filhos
		int [] coordPosVazia = new int[2];
		Nodo filho;
		coordPosVazia = getPosicaoDoValor(0,nodoPai.getTab());//pega local da posição vazia
		int l = coordPosVazia[0]; 
		int c = coordPosVazia[1];
		if(l < 2){//é possível mover para baixo
			filho = this.paraBaixo(nodoPai, coordPosVazia);//gera novo filho
			filho.setNodoPai(nodoPai);
			filhos.add(filho);
		}
		if(l > 0 ){//é possível mover para cima
			filho = new Nodo(this.paraCima(nodoPai, coordPosVazia).getTab());//gera novo filho
			filho.setNodoPai(nodoPai);
			filhos.add(filho);
		}
		if(c < 2){//é possível mover para a direita
			filho = new Nodo(this.paraDireita(nodoPai, coordPosVazia).getTab());//gera novo filho
			filho.setNodoPai(nodoPai);
			filhos.add(filho);
		}
		if(c > 0){//é possível mover para a esquerda
			filho = new Nodo(this.paraEsquerda(nodoPai, coordPosVazia).getTab());//gera novo filho
			filho.setNodoPai(nodoPai);
			filhos.add(filho);
		}
		return filhos;
		
	}
	//Efetua a troca para cima
	public Nodo paraCima(Nodo nodo, int [] coordPosVazia){
	
		int [][] tabuleiro  = this.copyMatriz(nodo.getTab());
		int lPosVazia = coordPosVazia[0];
		int cPosVazia = coordPosVazia[1];
		int valorParaTrocar = tabuleiro[lPosVazia-1][cPosVazia];//pega o valor acima, na mesma coluna
		//Troca o valor vazio com o valor da posicao acima
		tabuleiro[lPosVazia][cPosVazia] = valorParaTrocar;
		tabuleiro[lPosVazia-1][cPosVazia] = 0; 
		//Cria novo nodo levando o novo tabuleiro
		Nodo gerado = new Nodo(tabuleiro);
		
		return gerado;
	}
	//Efetua a troca para baixo
	public Nodo paraBaixo(Nodo nodo, int []coordPosVazia){
		int [][] tabuleiro  = this.copyMatriz(nodo.getTab());
		int lPosVazia = coordPosVazia[0];
		int cPosVazia = coordPosVazia[1];
		int valorParaTrocar = tabuleiro[lPosVazia+1][cPosVazia];//pega o valor abaixo, na mesma coluna
		//Troca o valor vazio com o valor da posicao abaixo
		tabuleiro[lPosVazia][cPosVazia] = valorParaTrocar;
		tabuleiro[lPosVazia+1][cPosVazia] = 0; 
		//Cria novo nodo levando o novo tabuleiro
		Nodo gerado = new Nodo(tabuleiro);
		
		return gerado;
	}
	//Efetua a troca para a direita
	public Nodo paraDireita(Nodo nodo, int []coordPosVazia){
		int [][] tabuleiro  = this.copyMatriz(nodo.getTab());
		int lPosVazia = coordPosVazia[0];
		int cPosVazia = coordPosVazia[1];
		int valorParaTrocar = tabuleiro[lPosVazia][cPosVazia+1];//pega o valor da direita, na mesma linha
		//Troca o valor vazio com o valor da direita
		tabuleiro[lPosVazia][cPosVazia] = valorParaTrocar;
		tabuleiro[lPosVazia][cPosVazia+1] = 0; 
		//Cria novo nodo levando o novo tabuleiro
		Nodo gerado = new Nodo(tabuleiro);
		
		return gerado;
	}
	//Efetua a troca para a esquerda
	public Nodo paraEsquerda(Nodo nodo, int []coordPosVazia){
		int [][] tabuleiro  = this.copyMatriz(nodo.getTab());
		int lPosVazia = coordPosVazia[0];
		int cPosVazia = coordPosVazia[1];
		int valorParaTrocar = tabuleiro[lPosVazia][cPosVazia-1];//pega o valor da esquerda, na mesma linha
		//Troca o valor vazio com o valor da esqueda
		tabuleiro[lPosVazia][cPosVazia] = valorParaTrocar;
		tabuleiro[lPosVazia][cPosVazia-1] = 0; 
		//Cria novo nodo levando o novo tabuleiro
		Nodo gerado = new Nodo(tabuleiro);
		
		return gerado;
	}
	//Retorna o valor da f(n) = g(n)+h(n)
	public int getFuncao(Nodo node){
		return this.getCusto(node)+this.getHeuristica(node.getTab());
	}
	//Cálculo do custo até determinado estado do tabuleiro
	public int getCusto(Nodo nodo){
		return nodo.getProfundidade();
	}
	//Cálculo das 3 heurísticas(Soma)
	//Cálculo da soma das 3 heuristicas
	public int getHeuristica(int[][]tabuleiro){
		return this.getQtdPecasForaDoLugar(tabuleiro)+
				this.getManhattanDistance(tabuleiro)+
				this.getQuantidadeTrocas(tabuleiro);
	}
	//Heuristica 1 : Contador da quantidade de peças fora do lugar
	//Cálculo da soma da quantidade de peças fora do lugar
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
	//Retorna o par [x,y] da posição do valor recebido por parâmetro
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
	//Retorna o par [x,y] da posição final do valor recebido por parâmetro
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
	/////////////////////////////////////////////////////////////////////////////////A ser excluido
	public void mostraTabuleiro(int [][] tabuleiro){
		
		System.out.println(tabuleiro[0][0] + "|" + tabuleiro[0][1] + "|" + tabuleiro[0][2]);
		System.out.println("- - -");
		System.out.println(tabuleiro[1][0] + "|" + tabuleiro[1][1] + "|" + tabuleiro[1][2]);
		System.out.println("- - -");
		System.out.println(tabuleiro[2][0] + "|" + tabuleiro[2][1] + "|" + tabuleiro[2][2] + "\n \n");
		
	}
	//Realiza a copia de uma determinada matriz
	public int[][] copyMatriz(int[][] matriz){
		int[][] copia = new int[3][3];
		
		for(int i = 0; i < matriz.length ; i++) {
			for(int j = 0; j < matriz[0].length; j++){
				copia[i][j] = matriz[i][j];
			}
		}
		
		return copia;
	}
}