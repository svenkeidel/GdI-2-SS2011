/**
 * 
 */
package logic.algorithm;

import java.security.InvalidAlgorithmParameterException;

/**
 * The factory for instantiate algorithms.
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 * 
 */
public class AlgorithmFactory {

	/**
	 * Gets a instance of an algorithm for provided value of the enum Algos.
	 * 
	 * @param algo
	 * @return a instance of the algorithm.
	 * @throws InvalidAlgorithmParameterException
	 *             if the algorithm does not exist.
	 */
	public static Algorithm getAlgorithm(Algos algo)
			throws InvalidAlgorithmParameterException {

		switch(algo) {

			case MOORE:
				// TODO: return a instance of Moore algorithm
				throw new InvalidAlgorithmParameterException("No such Algorithm");
				// break;

			case DIJKSTRA:
				// TODO: return a instance of Dijkstra algorithm
				throw new InvalidAlgorithmParameterException("No such Algorithm");
				// break;

			case ASTERN:
				// TODO: return a instance of A* algorithm
				throw new InvalidAlgorithmParameterException("No such Algorithm");
				// break;

			default:
				throw new InvalidAlgorithmParameterException("No such Algorithm");
		}

	}
}
