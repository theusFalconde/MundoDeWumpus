package wumpus;

import java.util.Iterator;
import java.util.LinkedList;

public class Grafo {

	public int vertices;

	public class Vertice {

		public String nome;

		public int x, y;

		public int indice;

		public int perigo;

		public Vertice(int x, int y, int indice, String nome) {
			this.x = x;
			this.y = y;
			this.indice = indice;
			this.nome = nome;

			if (MundoWumpus.VAZIO == nome) {
				this.perigo = -2;
			}
			if (MundoWumpus.FEDOR == nome) {
				this.perigo = -5;
			}
			if (MundoWumpus.BRISA == nome) {
				this.perigo = -5;
			}
			if (MundoWumpus.BRILHO == nome) {
				this.perigo = 0;
			}
			if (MundoWumpus.RUIDO == nome) {
				this.perigo = -1;
			}
			if (MundoWumpus.BURACO == nome) {
				this.perigo = -1000;
			}
			if (MundoWumpus.OURO == nome) {
				this.perigo = 1000;
			}
			if (MundoWumpus.WUMPUS == nome) {
				this.perigo = -1000;
			}
		}

	}

	public LinkedList<Vertice> mAdj[];

	@SuppressWarnings("unchecked")
	public Grafo(int vertices) {
		this.vertices = vertices;
		mAdj = new LinkedList[vertices];
		for (int i = 0; i < vertices; i++)
			mAdj[i] = new LinkedList<Vertice>();
	}

	public Vertice novoVertice(int indice, int x, int y, String nome) {
		return new Vertice(x, y, indice, nome);
	}

	public void addAresta(Vertice v, Vertice w) {
		mAdj[v.indice].add(w);
	}

	public void print() {
		for (int i = 0; i < vertices; i++) {
			LinkedList<Vertice> u = mAdj[i];
			System.out.print(i + " -> ");
			Iterator<Vertice> it = u.iterator();
			while (it.hasNext()) {
				Vertice v = it.next();
				System.out.print(v.indice + ", ");
			}
			System.out.println();
		}
	}

}
