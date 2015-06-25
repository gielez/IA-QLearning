package qLearning;

public interface DefinicaoQLearning {

	/**
	 * @return o fator de desconto.
	 */
	public double getGamma();

	/**
	 * @return a taxa de aprendizagem.
	 */
	public double getAlpha();
	
	/**
	 * @return os estados dispon�veis para executar.
	 */
	public int[] getEstados();
	
	/**
	 * @return rela��o entre o estado e suas a��es.
	 */
	public int[][] getAcaoEstado();
	
	/**
	 * @return o indice da lista de estados que corresponde ao estado final.
	 */
	public int getObjetivo();
	
	/**
	 * @return quantidade de epis�dios que devem ser executados pelo algoritmo. 
	 */
	public int nEpisodios();
	
	/**
	 * @return as recompensas para cada a��o executada.
	 */
	public int[][] getRecompensas();
	
	/**
	 * @return os nomes dos estados.
	 */
	public String[] getNomeEstados();
	
	/**
	 * @return os nomes das a��es.
	 */
	public String[] getNomeAcoes();

	public double[][] getQ();
}
