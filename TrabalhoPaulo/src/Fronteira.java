import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Fronteira {

    private int qntdMaxNodo;
    private List<Nodo> lista = new LinkedList<Nodo>();
    private List<Integer> listaRemovidos = new LinkedList<Integer>();
    
    
    public void adicionaNodos(List<Nodo> filhos) {
        for (Nodo nodo : filhos) {
            if(!listaRemovidos.contains(nodo.getTabuleiro().getIdentificador())){
                lista.add(nodo);
            }
        }
        if (lista.size() > qntdMaxNodo) {
            qntdMaxNodo = lista.size();
        }
    }

    public void removeNodo(Nodo nodoAtual) {
        listaRemovidos.add(nodoAtual.getTabuleiro().getIdentificador());
        lista.remove(nodoAtual);
    }

    public Nodo getProximoNodo() {
        if(!lista.isEmpty()){
        		return Collections.min(lista);
        }else{
        	return null;
        }
    }

    public int informaMaxNodo() {
        return qntdMaxNodo;
    }

	public void imprime() {
		/*System.out.println("Fronteira :");
		for(Nodo nodo : lista){
			System.out.println();
			System.out.println("Tabuleiro: "+nodo.getTabuleiro().getIdentificador());
			System.out.println("Comparativo: "+nodo.getComparativo());
			System.out.println("Heuristica: " + nodo.getHeuristica());
			System.out.println("Custo até aqui: " + nodo.getCustoAteAqui());
		}*/
	}

}
