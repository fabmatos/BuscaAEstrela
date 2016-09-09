
public class Tabuleiro {
	
	protected int [][] posicoes;
	
	public Tabuleiro(){
		
	}
	public Tabuleiro(int[][] posicoes){
		this.posicoes = new int[3][3];
		for(int l = 0; l < posicoes.length; l++){
			for(int c = 0; c < posicoes.length; c++){
				
			}
		}
		
	}

	public int[][] getPosicoes() {
		return posicoes;
	}

	public void setPosicoes(int[][] posicoes) {
		for(int l = 0; l<posicoes.length; l++){
			for(int c = 0; c < posicoes.length; c++){
				this.posicoes [l][c]= posicoes[l][c];
			}
		}
		
	}
	
	
	

}
