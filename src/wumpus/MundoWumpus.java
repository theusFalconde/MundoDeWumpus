package wumpus;

public class MundoWumpus {

	public static String VAZIO = "vazio";// -2;

	public static String FEDOR = "fedor";// -5;

	public static String BRISA = "brisa";// -5;

	public static String BRILHO = "brilho";// 0;

	public static String RUIDO = "ruido";// -1;

	public static String BURACO = "buraco";// -1000;

	public static String OURO = "ouro";// 1000;

	public static String WUMPUS = "wumpus";// -1000;

	public static boolean WUMPUS_STATUS = true;

	public static boolean OURO_STATUS = true;

	public static void main(String args[]) {
		Grafo g = new Grafo(4 * 4);

		Grafo.Vertice v11 = g.novoVertice(0, 1, 1, VAZIO);
		Grafo.Vertice v12 = g.novoVertice(1, 1, 2, BRISA);
		Grafo.Vertice v13 = g.novoVertice(2, 1, 3, BURACO);
		Grafo.Vertice v14 = g.novoVertice(3, 1, 4, BRISA);

		Grafo.Vertice v21 = g.novoVertice(4, 2, 1, BRILHO);
		Grafo.Vertice v22 = g.novoVertice(5, 2, 2, VAZIO);
		Grafo.Vertice v23 = g.novoVertice(6, 2, 3, BRISA);
		Grafo.Vertice v24 = g.novoVertice(7, 2, 4, VAZIO);

		Grafo.Vertice v31 = g.novoVertice(8, 3, 1, OURO);
		Grafo.Vertice v32 = g.novoVertice(9, 3, 2, BRILHO);
		Grafo.Vertice v33 = g.novoVertice(10, 3, 3, VAZIO);
		Grafo.Vertice v34 = g.novoVertice(11, 3, 4, FEDOR);

		Grafo.Vertice v41 = g.novoVertice(12, 4, 1, BRILHO);
		Grafo.Vertice v42 = g.novoVertice(13, 4, 2, VAZIO);
		Grafo.Vertice v43 = g.novoVertice(14, 4, 3, FEDOR);
		Grafo.Vertice v44 = g.novoVertice(15, 4, 4, WUMPUS);

		g.addAresta(v11, v12);
		g.addAresta(v11, v21);

		g.addAresta(v12, v11);
		g.addAresta(v12, v13);
		g.addAresta(v12, v22);

		g.addAresta(v13, v12);
		g.addAresta(v13, v14);
		g.addAresta(v13, v23);

		g.addAresta(v14, v13);
		g.addAresta(v14, v24);

		g.addAresta(v21, v11);
		g.addAresta(v21, v22);
		g.addAresta(v21, v31);

		g.addAresta(v22, v12);
		g.addAresta(v22, v21);
		g.addAresta(v22, v32);
		g.addAresta(v22, v23);

		g.addAresta(v23, v13);
		g.addAresta(v23, v24);
		g.addAresta(v23, v33);
		g.addAresta(v23, v22);

		g.addAresta(v24, v14);
		g.addAresta(v24, v23);
		g.addAresta(v24, v34);

		g.addAresta(v31, v21);
		g.addAresta(v31, v32);
		g.addAresta(v31, v41);

		g.addAresta(v32, v22);
		g.addAresta(v32, v33);
		g.addAresta(v32, v42);
		g.addAresta(v32, v31);

		g.addAresta(v33, v23);
		g.addAresta(v33, v34);
		g.addAresta(v33, v43);
		g.addAresta(v33, v32);

		g.addAresta(v34, v24);
		g.addAresta(v34, v33);
		g.addAresta(v34, v44);

		g.addAresta(v41, v31);
		g.addAresta(v41, v42);

		g.addAresta(v42, v41);
		g.addAresta(v42, v32);
		g.addAresta(v42, v43);

		g.addAresta(v43, v42);
		g.addAresta(v43, v33);
		g.addAresta(v43, v44);

		g.addAresta(v44, v34);
		g.addAresta(v44, v43);

		g.print();

		Agente a = new Agente("Agente X");
		a.buscaLargura(g, v11);

	}

}
