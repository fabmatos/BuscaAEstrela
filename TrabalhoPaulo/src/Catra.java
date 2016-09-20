
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Catra {

	public Tabuleiro moveBaixo(Tabuleiro tabuleiro, int[] posicaoVazia) {		
		Tabuleiro tabuleiroNovo = new Tabuleiro(copiaPosicoes(tabuleiro.getPosicoes()));
		int valorMovido = tabuleiro.getValor(posicaoVazia[0] + 1, posicaoVazia[1]);
		tabuleiroNovo.setPosicao(0, posicaoVazia[0] + 1, posicaoVazia[1]);
		tabuleiroNovo.setPosicao(valorMovido, posicaoVazia[0], posicaoVazia[1]);
		return tabuleiroNovo;
	}

	private int[][] copiaPosicoes(int[][] posicoes) {
		int[][] t = new int[3][3]; 
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				t[i][j] = posicoes[i][j];
			}
		}
		return t;
	}

	public Tabuleiro moveCima(Tabuleiro tabuleiro, int[] posicaoVazia) {
		Tabuleiro tabuleiroNovo = new Tabuleiro(copiaPosicoes(tabuleiro.getPosicoes()));
		int valorMovido = tabuleiro.getValor(posicaoVazia[0] - 1, posicaoVazia[1]);
		tabuleiroNovo.setPosicao(0, posicaoVazia[0] - 1, posicaoVazia[1]);
		tabuleiroNovo.setPosicao(valorMovido, posicaoVazia[0], posicaoVazia[1]);
		return tabuleiroNovo;
	}

	public Tabuleiro moveEsquerda(Tabuleiro tabuleiro, int[] posicaoVazia) {
		Tabuleiro tabuleiroNovo = new Tabuleiro(copiaPosicoes(tabuleiro.getPosicoes()));
		tabuleiroNovo.setPosicao(0, posicaoVazia[0], posicaoVazia[1] - 1);
		int valorMovido = tabuleiro.getValor(posicaoVazia[0], posicaoVazia[1] - 1);
		tabuleiroNovo.setPosicao(valorMovido, posicaoVazia[0], posicaoVazia[1]);
		return tabuleiroNovo;
	}

	public Tabuleiro moveDireita(Tabuleiro tabuleiro, int[] posicaoVazia) {
		Tabuleiro tabuleiroNovo = new Tabuleiro(copiaPosicoes(tabuleiro.getPosicoes()));
		int valorMovido = tabuleiro.getValor(posicaoVazia[0], posicaoVazia[1] + 1);
		tabuleiroNovo.setPosicao(0, posicaoVazia[0], posicaoVazia[1] + 1);
		tabuleiroNovo.setPosicao(valorMovido, posicaoVazia[0], posicaoVazia[1]);
		return tabuleiroNovo;
	}

	public int[] achaValor(Tabuleiro tabuleiro, int valor) {
		int posicao[] = new int[2];
		for (int linha = 0; linha < 3; linha++) {
			for (int coluna = 0; coluna < 3; coluna++) {
				if (tabuleiro.getPosicoes()[linha][coluna] == valor) {
					posicao[0] = linha;
					posicao[1] = coluna;
					return posicao;
				}
			}
		}
		return posicao;
	}

	public List<Nodo> geraFilhos(Nodo nodo) {
		List<Nodo> filhos = new ArrayList<Nodo>();
		int[] posicaoVazia = achaValor(nodo.getTabuleiro(), 0);
		Tabuleiro tabuleiroFilho;
		Nodo nodoFilho;
		if (posicaoVazia[0] < 2) {
			tabuleiroFilho = moveBaixo(nodo.getTabuleiro(), posicaoVazia);
			nodoFilho = new Nodo(tabuleiroFilho);
			nodoFilho.setPai(nodo);
			filhos.add(nodoFilho);
		}
		if (posicaoVazia[0] > 0) {
			tabuleiroFilho = moveCima(nodo.getTabuleiro(), posicaoVazia);
			nodoFilho = new Nodo(tabuleiroFilho);
			nodoFilho.setPai(nodo);
			filhos.add(nodoFilho);
		}
		if (posicaoVazia[1] > 0) {
			tabuleiroFilho = moveEsquerda(nodo.getTabuleiro(), posicaoVazia);
			nodoFilho = new Nodo(tabuleiroFilho);
			nodoFilho.setPai(nodo);
			filhos.add(nodoFilho);
		}
		if (posicaoVazia[1] < 2) {
			tabuleiroFilho = moveDireita(nodo.getTabuleiro(), posicaoVazia);
			nodoFilho = new Nodo(tabuleiroFilho);
			nodoFilho.setPai(nodo);
			filhos.add(nodoFilho);
		}
		return filhos;
	}
}