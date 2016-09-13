
public class Nodo {
	
	protected Nodo nodoPai;
	protected Tabuleiro tab;
	protected int profundidade;

	public int getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(int profundidade) {
		this.profundidade = profundidade;
	}

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

}
