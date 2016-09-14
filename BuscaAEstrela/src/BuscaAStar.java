
public class BuscaAStar {
	
	protected Nodo nodoPai;
	protected int qtdEstados; //Quantidade total de estados percorridos ate ordenacao
	protected int qtdNodosFronteira; //Maior quantidade de nodos da fronteira
	
	//Heuristica 1
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
        
        for (int numero = 1; numero < 9; numero++) {
        	int [] coordenadas = new int [2];
        	coordenadas = this.getPosicaoDoValor(numero, tabuleiro);
        	int l = coordenadas[0];
			int c = coordenadas[1];
			
        }
        
        return quantDuplas*2;
		
	}
	
	public int [][] getNodoObjetivo(){
		int setaPosicao = 1; //seta as posicoes objetivo do tabuleiro
		int [][] objetivo = new int [3][3];
		for (int l = 0; l < objetivo.length; l++){//percorre e insere o tabuleiro objetivo
			for (int c = 0; c < objetivo.length; c++){
				objetivo[l][c] = setaPosicao;
				setaPosicao++;
			}
		}
		objetivo[2][2] = 0;//última posição com a casa branca
		return objetivo;
	}
	
	public boolean ehNodoObjetivo(int [][] nodo){
		int qtdIguais = 0;//conta quantidade de valores nodo = objetivo
		int [][] objetivo = getNodoObjetivo();//pega nodo objetivo
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
	public boolean valorEstaEmPosicaoInversaoDireta(int valor){
		
		boolean retorno = false;
		int [] coordenadasObjetivo = new int [2];
		coordenadasObjetivo = this.getPosicaoFinalDoValor(valor);
		int l = coordenadasObjetivo[0];
		int c = coordenadasObjetivo[1];
		
		switch (valor){
		case 1:
			if((l==0 && c==1)||(l==1 && c==0)){
				retorno = true;
				break;
			}
		case 2:
			if((l==0 && c==2)||(l==1 && c==1)||(l==0 && c==0)){
				retorno = true;
				break;
			}	
		case 3:
			if((l==1 && c==2)||(l==0 && c==1)){
				retorno = true;
				break;
			}
		case 4:
			if((l==0 && c==0)||(l==1 && c==1)||(l==2 && c==0)){
				retorno = true;
				break;
			}
		case 5:
			if((l==0 && c==1)||(l==1 && c==0)||(l==2 && c==1)||(l==1 && c==2)){
				retorno = true;
				break;
			}
		case 6:
			if((l==0 && c==2)||(l==1 && c==1)||(l==2 && c==2)){
				retorno = true;
				break;
			}	
		case 7:
			if((l==1 && c==0)||(l==2 && c==1)){
				retorno = true;
				break;
			}	
		case 8:
			if((l==1 && c==1)||(l==2 && c==0)||(l==2 && c==2)){
				retorno = true;
				break;
			}	
		}
	return retorno;
		
	}
	
}
