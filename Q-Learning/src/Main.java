package AlgoritmoQLearning.bin;
public class Main {

	public static void main(String[] args) {
		final double alpha = 0.1;
		final double gamma = 0.9;

		// _______
		// |A|B|C|
		// |_____|
		// |D|E|F|
		// |_____|
		//

		final int estadoA = 0;
		final int estadoB = 1;
		final int estadoC = 2;
		final int estadoD = 3;
		final int estadoE = 4;
		final int estadoF = 5;

		final int quantEstados = 6;
		final int[] estados = new int[] { estadoA, estadoB, estadoC, estadoD,
				estadoE, estadoF };

		// Q(s,a)= Q(s,a) + alpha * (R(s,a) + gamma * Max(next state, all
		// actions) -
		// Q(s,a))

		int[][] recompensa = new int[quantEstados][quantEstados]; // reward lookup
		double[][] qlearning = new double[quantEstados][quantEstados]; // Q learning

		int[] acoesParaA = new int[] { estadoB, estadoD };
		int[] acoesParaB = new int[] { estadoA, estadoC, estadoE };
		int[] acoesParaC = new int[] { estadoC };
		int[] acoesParaD = new int[] { estadoA, estadoE };
		int[] acoesParaE = new int[] { estadoB, estadoD, estadoF };
		int[] acoesParaF = new int[] { estadoC, estadoE };
		int[][] acoes = new int[][] { acoesParaA, acoesParaB,
				acoesParaC, acoesParaD, acoesParaE, acoesParaF };

		String[] nomesEstados = new String[] { "A", "B", "C", "D", "E", "F" };

		recompensa[estadoB][estadoC] = 100; // from b to c
		recompensa[estadoF][estadoC] = 100; // from f to c

		Algoritmo algoritmo = new Algoritmo(gamma, alpha, estados, acoes, 2,
				1000, recompensa, qlearning, nomesEstados);
		algoritmo.novoQLearning();
		algoritmo.showPolicy();
		algoritmo.printResult();
	}

}
