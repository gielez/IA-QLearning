package exemplos;

import qLearning.DefinicaoQLearning;
import qLearning.QLearning;

public class LabirintoQLearning implements DefinicaoQLearning{
	
	final int estadoA = 0;
	final int estadoB = 1;
	final int estadoC = 2;
	final int estadoD = 3;
	final int estadoE = 4;
	final int estadoF = 5;

	public double getGamma() {
		return 0.9;
	}

	public double getAlpha() {
		return 0.1;
	}

	public int[] getEstados() {
		int[] estados = new int[] { estadoA, estadoB, estadoC, estadoD,
				estadoE, estadoF };
		return estados;
	}

	public int[][] getAcaoEstado() {
		int[] acoesParaA = new int[] { estadoB, estadoD };
		int[] acoesParaB = new int[] { estadoA, estadoC, estadoE };
		int[] acoesParaC = new int[] { estadoC };
		int[] acoesParaD = new int[] { estadoA, estadoE };
		int[] acoesParaE = new int[] { estadoB, estadoD, estadoF };
		int[] acoesParaF = new int[] { estadoC, estadoE };
		int[][] acoes = new int[][] { acoesParaA, acoesParaB,
				acoesParaC, acoesParaD, acoesParaE, acoesParaF };
		return acoes;
	}

	public int getObjetivo() {
		return estadoC;
	}

	public int nEpisodios() {
		return 1000;
	}

	public int[][] getRecompensas() {
		int[][] recompensa = new int[getEstados().length][getEstados().length];
		recompensa[estadoB][estadoC] = 100; 
		recompensa[estadoF][estadoC] = 100;
		return recompensa;
	}

	public String[] getNomeEstados() {
		String[] nomesEstados = new String[] { "A", "B", "C", "D", "E", "F" };
		return nomesEstados;
	}

	public String[] getNomeAcoes() {
		String[] nomesAcoes = new String[] { "Ação A", "Ação B", "Ação C", "Ação D", "Ação E", "Ação F" };
		return nomesAcoes;
	}
	
	public double[][] getQ() {
		double[][] qlearning = new double[getEstados().length][getEstados().length]; // Q learning
		return qlearning;
	}
	
	public static void main(String[] args) {
		DefinicaoQLearning estados = new LabirintoQLearning();
		QLearning algoritmo = new QLearning(estados);
		algoritmo.executeQLearning();
		algoritmo.printResultado();
	}

}
