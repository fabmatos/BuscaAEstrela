
public class Tabuleiro {
	
	protected int [][] posicoes;
	protected int idTab;
	
	public Tabuleiro(int [][] pos){
		this.posicoes = pos;
		this.setIdTab();
	}
	
	public int getIdTab() {
		return idTab;
	}
	
	public void setIdTab(){
		StringBuilder id = new StringBuilder();
		for (int l = 0; l < 3; l++) {
			for (int c = 0; c < 3; c++) {
				id.append(posicoes[l][c]);
			}
		}
		idTab = Integer.parseInt(id.toString());
	}

	public int[][] getPosicoes() {
		return posicoes;
	}
	
	public int getValorPos(int l, int c){
		return this.posicoes[l][c];
	}
	
	public int [] getCoordenadasN(int n){
		int [] coord = new int[2];
		for(int l = 0; l < 3; l++){
			for(int c = 0; c < 3; c++){
				if(this.posicoes[l][c]==n){
					coord[0] = l;
					coord[1] = c;
				}
			}
		}
		return coord;
	}
	
	
}
