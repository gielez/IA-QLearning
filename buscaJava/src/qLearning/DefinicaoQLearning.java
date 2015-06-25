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
	 * @return os estados disponíveis para executar.
	 */
	public int[] getEstados();
	
	/**
	 * @return relação entre o estado e suas ações.
	 */
	public int[][] getAcaoEstado();
	
	/**
	 * @return o indice da lista de estados que corresponde ao estado final.
	 */
	public int getObjetivo();
	
	/**
	 * @return quantidade de episódios que devem ser executados pelo algoritmo. 
	 */
	public int nEpisodios();
	
	/**
	 * @return as recompensas para cada ação executada.
	 */
	public int[][] getRecompensas();
	
	/**
	 * @return os nomes dos estados.
	 */
	public String[] getNomeEstados();
	
	/**
	 * @return os nomes das ações.
	 */
	public String[] getNomeAcoes();

	public double[][] getQ();
}
