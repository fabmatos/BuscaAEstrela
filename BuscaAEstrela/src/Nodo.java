
public class Nodo implements Comparable<Nodo>{
	
	protected Nodo nodoPai;
	protected int[][] tab = new int[3][3];
	protected int profundidade;
	
	public Nodo(int [][]tabuleiro){
		for(int l = 0; l< 3; l++){
			for(int c = 0; c < 3; c++){
				this.tab[l][c] = tabuleiro[l][c];
			}
		}
	}
	
	public Nodo(){
		
	}

	public int getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(int profundidade) {
		this.profundidade = profundidade;
	}

	public int[][] getTab() {
		return this.tab;
	}

	public void setTab(int [][] tabuleiro) {
		for(int l = 0; l< 9; l++){
			for(int c = 0; c < 9; c++){
				this.tab[l][c] = tabuleiro[l][c];
			}
		}
	}

	public Nodo getNodoPai() {
		return nodoPai;
	}

	public void setNodoPai(Nodo nodoPai) {
		this.nodoPai = nodoPai;
	}

	@Override
	public int compareTo(Nodo o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
