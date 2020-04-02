package wumpus;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Agente caçador de wumpus e ouro
 * 
 * Assume-se que:
 * 
 * - O agente nunca morre, pois ele sempre desvia o wumpus e os buracos; - O
 * agente ganha o jogo quando coleta o ouro se o mesmo estiver ali; - O agente
 * para quando termina o jogo ou quando todos os objetivos no mundo foram
 * completos.
 * 
 */
public class Agente {

	public String nome;

	public int pontos;

	public int flechas;

	boolean visitado[];

	public boolean status; // true para vivo e false para morto

	LinkedList<Grafo.Vertice> historico;// !< pilha de vertices visitados

	public Agente(String nome) {
		this.pontos = 0;
		this.flechas = 1;
		this.nome = nome;
		this.status = true;
		this.historico = new LinkedList<Grafo.Vertice>();
	}

	/**
	 * Realiza uma busca em largura em um dado um grafo G
	 * 
	 * @param g    - grafo
	 * @param vIni - vertice inicial
	 */
	public void buscaLargura(Grafo g, Grafo.Vertice vIni) {
		this.visitado = new boolean[g.vertices];
		Grafo.Vertice vProx = null;
		for (int i = 0; i < g.vertices; i++) {
			visitado[i] = false;
		}
		visitado[vIni.indice] = true;
		vProx = vIni;
		historico.add(vProx);
		/**
		 * O agente caminha no grafo enquanto estiver vivo.
		 */
		while (pontos > -1000) {
			Grafo.Vertice vAtual = vProx;
			if (vAtual.nome == MundoWumpus.OURO) {
				// Pega o ouro se estiver no local
				// Recebe seus pontos e volta ao topo
				if (MundoWumpus.OURO_STATUS) {
					MundoWumpus.OURO_STATUS = false;
					System.out.println("Agente: Peguei o ouro !!!");
					if (MundoWumpus.WUMPUS_STATUS == false) {
						System.out.println("Agente: Wumpus esta morto ...");
						voltarAoTopo();
						break;
					}
				} else {
					// Se o ouro nao esta no local e o wumpus esta morto entao o jogo acabou.
					if (MundoWumpus.WUMPUS_STATUS == false) {
						System.out.println("Agente: Objetivos completos, não tenho mais o que fazer ...");
						return;
					}
				}
			}
			int perigo = -9000;
			if (!(vAtual.nome == MundoWumpus.OURO))
				pontos += vAtual.perigo; // Os pontos do agente sao atualizados a cada passo dado.
			System.out.println("casa -> [" + vAtual.x + ", " + vAtual.y + "] pontos = " + pontos);
			Iterator<Grafo.Vertice> it = g.mAdj[vAtual.indice].iterator();
			/**
			 * O agente olha para todos os vertices adjacentes e escolhe qual vertice
			 * adjacente sera o proximo passo com base no perigo de cada vertice adjacente.
			 * A prioridade e sempre ir pelo caminho menos perigoso.
			 */
			System.out.print("Opcoes -> ");
			while (it.hasNext()) {
				Grafo.Vertice vFoco = (Grafo.Vertice) it.next();
				if (!visitado[vFoco.indice]) {
					visitado[vFoco.indice] = true;
					/**
					 * Se o vertice que o agente estiver olhando for o wumpus VIVO, entao atira uma
					 * flecha se tiver flecha entao o wumpus morre, caso contrario o agente tera que
					 * realizar outra acao.
					 */
					if (MundoWumpus.WUMPUS_STATUS && vFoco.nome == MundoWumpus.WUMPUS) {
						if (atirarFlecha(vFoco.x, vFoco.y)) {
							vFoco.perigo = -1;
							MundoWumpus.WUMPUS_STATUS = false;
							System.out.println("Wumpus grita: WWWWWAAAAHGGGGGG !!!\nWumpus esta morto!");
						}
						if (MundoWumpus.OURO_STATUS == false) {
							System.out.println("Agente: Wumpus esta morto e o ouro foi pego ...");
							voltarAoTopo();
							return;
						}
					}
					if (vFoco.perigo >= perigo) {
						perigo = vFoco.perigo;
						vProx = vFoco;
						System.out.print("[" + vProx.x + ", " + vProx.y + "](" + vFoco.perigo + "), ");
					}
				}
			}
			System.out.print("\nEscolheu a casa -> [" + vProx.x + ", " + vProx.y + "]\n");
			historico.add(vProx);// O agente armazena em sua memoria o caminho feito adicionando cada passo em
									// uma pilha.
			System.out.println("\n");
			perigo = -9000;
		}
		if (historico.isEmpty()) {
			pontos += 1000;
			System.out.println("Agente: Objetivo completo !!!");
		}
		if (pontos < -1000) {
			System.out.println("O " + this.nome + " morreu ... =(");
		}
	}

	/**
	 * Atira uma flecha em uma coordenada
	 * 
	 * @param x - coordenada x
	 * @param y - coordenada y
	 * @return true caso tenha atirado com sucesso e falso para o contrario
	 */
	public boolean atirarFlecha(int x, int y) {
		flechas = flechas - 1;
		pontos = pontos - 10;
		if (flechas < 0) {
			System.out.println("Agente Tenta atirar uma flecha. Ops, não há flechas !");
			pontos = pontos - 1;
			return false;
		} else {
			System.out.println("Agente Atira uma flecha !");
			return true;
		}

	}

	public void voltarAoTopo() {
		System.out.println("Agente: Vou voltar para o topo da caverna ...");
		historico.removeLast();
		while (!historico.isEmpty()) {
			Grafo.Vertice vAtual = historico.getLast();
			pontos += vAtual.perigo;
			System.out.println("Casa -> [" + vAtual.x + ", " + vAtual.y + "] (" + pontos + ")");
			historico.removeLast();
		}

	}

}
