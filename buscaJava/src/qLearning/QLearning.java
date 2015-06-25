package qLearning;

import java.text.DecimalFormat;
import java.util.Random;

public class QLearning {
	
final DecimalFormat df = new DecimalFormat("#.##");
	
	private double gamma;
	private double alpha;
	private int[] estados;
	private int[][] acaoEstado;
	private int objetivo;
	private int nEpisodios;
	private int[][] recompensa;
	private double[][] qLearning;
	private String[] nomeEstados;

	public QLearning(DefinicaoQLearning estados) {
		this.gamma = estados.getGamma();
		this.alpha = estados.getAlpha();
		this.estados = estados.getEstados();
		this.acaoEstado = estados.getAcaoEstado();
		this.objetivo = estados.getObjetivo();
		this.nEpisodios = estados.nEpisodios();
		this.recompensa = estados.getRecompensas();
		this.nomeEstados = estados.getNomeEstados();
		this.qLearning = estados.getQ();
	}
	
	/***
	 * Inicialize Q(s, a) arbitrariamente 
	 * Repita (para cada episódio) 
	 * Inicialize s 
	 * Repita para cada estado s do episódio 
	 * Escolha a ação a pertence a A(s,a) 
	 * Execute a ação a no estado s 
	 * Observe os valores s' e r 
	 * Q(s,a) = Q(s,a)+alfa[r + y* (maxQ(s', a')) - Q(s,a)] 
	 * s = s' 
	 * Até que termine
	 * 
	 * Executa o algoritmo de Q-Learning.
	 * 
	 * @param quantidadeEstados
	 *            quantidade de estados.
	 */
	public void executeQLearning() {
		Random rand = new Random();

		//Atingir N episodios
		for (int i = 0; i < nEpisodios; i++) {
			int estado = rand.nextInt(estados.length);

			while (estado != objetivo) {
				
				//Inicializa Q(s, a)
				int[] acoes = acaoEstado[estado];

				// seleciona a ação
				int index = rand.nextInt(acoes.length);
				int acao = acoes[index];
				
				// Observa os valores de recompensa e próximo estado
				int recompensa = recompensa(estado, acao);
				int proximoEstado = acao;
				
				// Q(s,a) =    (1- alpha)*Q(s,a)+alpha[r + y* (maxQ(s', a'))] 
				double q = Q(estado, acao);
				double maxQ = maxQ(proximoEstado);
				double valor = (1 - alpha) * q + alpha * (recompensa + gamma * maxQ);
				
				setQ(estado, acao, valor);
				
				//s = s'
				estado = proximoEstado;
			}
		}
	}

	double Q(int s, int a) {
		return qLearning[s][a];
	}
	
	void setQ(int s, int a, double valor) {
		qLearning[s][a] = valor;
	}
	
	int recompensa(int s, int a) {
		return recompensa[s][a];
	}

	double maxQ(int s) {
		int[] acoesDoEstado = acaoEstado[s];
		double valorMaximo = Double.MIN_VALUE;
		for (int i = 0; i < acoesDoEstado.length; i++) {
			int proximoEstado = acoesDoEstado[i];
			double valor = qLearning[s][proximoEstado];

			if (valor > valorMaximo)
				valorMaximo = valor;
		}
		return valorMaximo;
	}

	public void printResultado() {
		System.out.println("Print");
		for (int i = 0; i < qLearning.length; i++) {
			System.out.print(nomeEstados[i] + "-");
			String str = "";
			for (int j = 0; j < qLearning[i].length; j++) {
				str += " \"" + df.format(qLearning[i][j]) + "\"    ";
			}
			System.out.println(str);
		}
	}
}
