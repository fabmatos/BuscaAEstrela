import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Scanner;

public class Vish {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[][] posicoes = new int[3][3];

		Scanner entrada = new Scanner(System.in);
		System.out.println("Olá! Eu sou o Vish. Meu amigo mito vai me ajudar a resolver seu desafio!");
		System.out.println("Para informar espaco em branco utilize o numero 0!");
		System.out.println("Informe os valores da primeira linha do tabuleiro:");
		posicoes[0][0] = entrada.nextInt();
		posicoes[0][1] = entrada.nextInt();
		posicoes[0][2] = entrada.nextInt();
		System.out.println("Informe os valores da segunda linha do tabuleiro:");
		posicoes[1][0] = entrada.nextInt();
		posicoes[1][1] = entrada.nextInt();
		posicoes[1][2] = entrada.nextInt();
		System.out.println("Informe os valores da terceira linha do tabuleiro:");
		posicoes[2][0] = entrada.nextInt();
		posicoes[2][1] = entrada.nextInt();
		posicoes[2][2] = entrada.nextInt();
		System.out.println("Lá vou eu!");

		long tempoInicial = System.currentTimeMillis();

		Tabuleiro tabuleiro = new Tabuleiro(posicoes);
		Mito mito = new Mito();
		Nodo nodo = new Nodo(tabuleiro);
		mito.visitaNodo(nodo);
		System.out.println(
				"Gastei pouco tempo, apenas " + (System.currentTimeMillis() - tempoInicial) + " milisegundos!");
	}

}