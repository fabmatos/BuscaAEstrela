import java.util.HashMap;

public class Nodo implements Comparable<Nodo> {

	private Tabuleiro tabuleiro;
	private Nodo pai;
	private int custoAteAqui;
	private int heuristica;
	private HashMap<String, Integer> posicoesCruzadas = new HashMap<>();

	public Nodo(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		calculaHeuristica();
	}

	private void calculaHeuristica() {
		heuristica = 0;
		int[][] posFinais = { { 1, 2, 3 }, { 8, 0, 4 }, { 7, 6, 5 } };
		Tabuleiro tabuleiroFinal = new Tabuleiro(posFinais);
		int valor = 0, coluna = 0, linha = 0;
		int[] posValorAtual;
		int[] posValorFinal;
		String key;
		for (int i = 0; i < 9; i++) {
			posValorFinal = tabuleiroFinal.getPosValor(valor);
			posValorAtual = tabuleiro.getPosValor(valor);
			linha = posValorFinal[0] - posValorAtual[0];
			coluna = posValorFinal[1] - posValorAtual[1];
			if (i > 0) {
				if (linha == 0 && coluna != 0) {
					key = "C" + posValorAtual[0];
					if (coluna > 0) {
						key += "D";
						posicoesCruzadas.put(key, 1);
					} else if (coluna < 0) {
						key += "E";
						posicoesCruzadas.put(key, 1);
					}
				} else if (linha != 0 && coluna == 0) {
					key = "L" + posValorAtual[1];
					if (linha > 0) {
						key += "B";
						posicoesCruzadas.put(key, 1);
					} else if (linha < 0) {
						key += "C";
						posicoesCruzadas.put(key, 1);
					}
				}
			}
			valor++;
			heuristica += Math.abs(coluna) + Math.abs(linha);
		}
		calculaCruzadas();
	}

	private void calculaCruzadas() {
		// Pra tentar salvar o desempenho evitei for
		heuristica += containKeys("C0D", "C0E");
		heuristica += containKeys("C1D", "C1E");
		heuristica += containKeys("C2D", "C2E");
		heuristica += containKeys("L0B", "L0C");
		heuristica += containKeys("L1B", "L1C");
		heuristica += containKeys("L2B", "L2C");
	}

	private int containKeys(String k1, String k2) {
		if (posicoesCruzadas.containsKey(k1) && posicoesCruzadas.containsKey(k2)) {
			return 2;
		} else {
			return 0;
		}

	}

	public Nodo getPai() {
		return pai;
	}
	
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public void calculaCusto() {
		if (pai != null)
			custoAteAqui = pai.custoAteAqui + 1;
		else
			custoAteAqui = 1;
	}

	@Override
	public int compareTo(Nodo o) {
		if (this.getComparativo() > o.getComparativo()) {
			return 1;
		} else if (this.getComparativo() < o.getComparativo()) {
			return -1;
		}
		return 0;
	}

	public int getComparativo() {
		return pai.getCustoAteAqui() + this.heuristica;
	}

	public int getHeuristica(){
		return heuristica;
	}
	public int getCustoAteAqui() {
		return custoAteAqui;
	}

	public void setPai(Nodo nodo) {
		this.pai = nodo;
	}

	@Override
	public boolean equals(Object obj) {
		Nodo nodo = (Nodo) obj;
		return this.getTabuleiro().getIdentificador() == nodo.getTabuleiro().getIdentificador();
	}

}

