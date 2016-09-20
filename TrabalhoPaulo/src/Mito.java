import java.util.List;
import java.util.Stack;

public class Mito {

	private Fronteira fronteira = new Fronteira();

	public void visitaNodo(Nodo nodoAtual) {
		if (ehObjetivo(nodoAtual)) {
			System.out.println("Quantidade Max. de nodos na fronteira: " + fronteira.informaMaxNodo());
			informaCaminhoEncontrado(nodoAtual);

		} else {
			nodoAtual.calculaCusto();
			List<Nodo> filhos = geraFilhos(nodoAtual);
			fronteira.adicionaNodos(filhos);
			fronteira.removeNodo(nodoAtual);
			nodoAtual = fronteira.getProximoNodo();
			if(nodoAtual != null){
				visitaNodo(nodoAtual);
			}else{
				System.out.println("Ops, parece que não encontramos nenhuma solução");
			}
		}
	}

	private List<Nodo> geraFilhos(Nodo nodoAtual) {
		Catra catra = new Catra();
		return catra.geraFilhos(nodoAtual);
	}

	private void informaCaminhoEncontrado(Nodo nodoFinal) {
		boolean acabouJessica = false;
		Stack<Nodo> stack = new Stack<>();
		Nodo aux;
		while (!acabouJessica) {
			stack.push(nodoFinal);
			if (nodoFinal.getPai() != null) {
				aux = nodoFinal.getPai();
				nodoFinal = aux;
			} else {
				acabouJessica = true;
			}
		}
		int tamanhoPilha = stack.size();
		System.out.println("Profundidade: " + (tamanhoPilha - 1));
		for (int i = 0; i < tamanhoPilha; i++) {
			aux = stack.pop();
			imprimeTabuleiro(aux.getTabuleiro(), i);
		}

	}

	public void imprimeTabuleiro(Tabuleiro tabuleiro, int i) {
		if (i == 0) {
			System.out.println("Tabuleiro inicial:");
		} else {
			System.out.println("Jogada: " + i);
		}
		int[][] pocisoes = tabuleiro.getPosicoes();
		System.out.println(pocisoes[0][0] + "|" + pocisoes[0][1] + "|" + pocisoes[0][2]);
		System.out.println("- - -");
		System.out.println(pocisoes[1][0] + "|" + pocisoes[1][1] + "|" + pocisoes[1][2]);
		System.out.println("- - -");
		System.out.println(pocisoes[2][0] + "|" + pocisoes[2][1] + "|" + pocisoes[2][2] + "\n \n");
	}

	private boolean ehObjetivo(Nodo nodoAtual) {
		return nodoAtual.getTabuleiro().getIdentificador() == 123804765;
	}

}
