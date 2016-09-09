
public class Nodo {
	
	protected Nodo nodoPai;
	protected Tabuleiro tab;

	public Tabuleiro getTab() {
		return tab;
	}

	public void setTab(Tabuleiro tab) {
		this.tab = tab;
	}

	public Nodo getNodoPai() {
		return nodoPai;
	}

	public void setNodoPai(Nodo nodoPai) {
		this.nodoPai = nodoPai;
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

	public int [][] getNodoObjetivo(){//Tabuleiro tab){
		int setaPosicao = 1; //seta as posicoes objetivo do tabuleiro
		int [][] objetivo = new int [3][3];
		for (int l = 0; l < objetivo.length; l++){//percorre e insere o tabuleiro objetivo
			for (int c = 0; c < objetivo.length; c++){
				objetivo[l][c] = setaPosicao;
				setaPosicao++;
			}
		}
		objetivo[2][2] = 0;
		return objetivo;
	}

}
