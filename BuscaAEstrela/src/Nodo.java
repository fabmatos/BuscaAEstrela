
public class Nodo implements Comparable<Nodo>{
	
	protected int idNodo;
	protected Nodo nodoPai;
	protected int[][] tab;
	protected int profundidade;
	protected int custoTotal;
	
	public Nodo(int [][]tabuleiro){
		this.tab = tabuleiro;
		this.setIdNodo();
				
	}
	
	public Nodo(){
		this.setIdNodo();
	}
	
	public int getCustoTotal(){
		return this.custoTotal;
	}
	
	public void setCustoTotal(int valorCusto){
		this.custoTotal = valorCusto;
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
	
	public int[] getPosicaoVazia(){
		int []coords = new int[2];
		for(int l = 0; l< 9; l++){
			for(int c = 0; c < 9; c++){
				if(this.tab[l][c] == 0){
					coords[0]=l;
					coords[1]=c;
				}
			}
		}
		return coords;
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
	public void setIdNodo(){
		StringBuilder id = new StringBuilder();
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				id.append(tab[i][j]);
			}
		}
		idNodo = Integer.parseInt(id.toString());
	}
	public int getIdNodo(){
		return this.idNodo;
	}

}