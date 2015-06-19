import java.text.DecimalFormat;
import java.util.Random;

public class Algoritmo {
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

	public Algoritmo(double gamma, double alfa, int[] estados, int[][] acoes, int objetivo, int nEpisodios, int[][] recompensa, double[][] qLearning, String[] nomeEstados) {
		this.gamma = gamma;
		this.alpha = alfa;
		this.estados = estados;
		this.acaoEstado = acoes;
		this.objetivo = objetivo;
		this.nEpisodios = nEpisodios;
		this.recompensa = recompensa;
		this.qLearning = qLearning;
		this.nomeEstados = nomeEstados;
	}
	
	/***
	 * Inicialize Q(s, a) arbitrariamente 
	 * Repita (para cada episódio) 
	 * Inicialize s 
	 * Repita para cada estado s do episódio 
	 * Escolha a ação a pertence a A(s,a) 
	 * Execute a ação a no estado s 
	 * Observe os valores s' e r 
	 * Q(s,a) = Q(s,a)+alfa[r + y* (maxQ(s', a')) - Q(s,a)] s = s' 
	 * Até que termine
	 * 
	 * a = ação 
	 * s = estado 
	 * r = recompensa 
	 * alfa = taxa de aprendizagem 
	 * y = fator de desconto
	 * Q(s, a) = valor de atualização do q-learning para o par
	 * estado-ação.
	 */
	
	/**
	 * Executa o algoritmo de Q-Learning.
	 * 
	 * @param quantidadeEstados
	 *            quantidade de estados.
	 */
	public void novoQLearning() {
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
				int recompensa = R(estado, acao);
				int nextState = acao;
				
				//Q(s,a) = Q(s,a)+alfa[r + y* (maxQ(s', a')) - Q(s,a)] 
				double q = Q(estado, acao);
				double maxQ = maxQ(nextState);
//				double value = q + alpha * (recompensa + gamma * maxQ - q);
				double value = (1 - alpha) * q + alpha * (recompensa + gamma * maxQ);
				
				setQ(estado, acao, value);
				//s = s'
				estado = nextState;
			}
		}
	}

	double maxQ(int s) {
		int[] actionsFromState = acaoEstado[s];
		double maxValue = Double.MIN_VALUE;
		for (int i = 0; i < actionsFromState.length; i++) {
			int nextState = actionsFromState[i];
			double value = qLearning[s][nextState];

			if (value > maxValue)
				maxValue = value;
		}
		return maxValue;
	}

	// get policy from state
	int policy(int state) {
		int[] actionsFromState = acaoEstado[state];
		double maxValue = Double.MIN_VALUE;
		int policyGotoState = state; // default goto self if not found
		for (int i = 0; i < actionsFromState.length; i++) {
			int nextState = actionsFromState[i];
			double value = qLearning[state][nextState];

			if (value > maxValue) {
				maxValue = value;
				policyGotoState = nextState;
			}
		}
		return policyGotoState;
	}

	double Q(int s, int a) {
		return qLearning[s][a];
	}

	void setQ(int s, int a, double value) {
		qLearning[s][a] = value;
	}

	int R(int s, int a) {
		return recompensa[s][a];
	}

	void printResult() {
		System.out.println("Print");
		for (int i = 0; i < qLearning.length; i++) {
			System.out.print("out from " + nomeEstados[i] + ":  ");
			for (int j = 0; j < qLearning[i].length; j++) {
				System.out.print(df.format(qLearning[i][j]) + " ");
			}
			System.out.println();
		}
	}

	// policy is maxQ(states)
	void showPolicy() {
		System.out.println("\nshowPolicy");
		for (int i = 0; i < estados.length; i++) {
			int from = estados[i];
			int to = policy(from);
			System.out.println("de " + nomeEstados[from] + " para "
					+ nomeEstados[to]);
		}
	}

}
