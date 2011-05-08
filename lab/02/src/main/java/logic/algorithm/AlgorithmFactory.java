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

			case Moore:
				return new Moore();
				// break;

			case Dijkstra:
				return new Dijkstra();
				// break;

			case A_Star:
				return new A_Star();
				// break;

			default:
				throw new InvalidAlgorithmParameterException("No such Algorithm");
		}

	}
}
