package logic.algorithm;

import datamodel.Grid;

/**
 * Defines the interface for every algorithm
 *
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 *
 */
public interface Algorithm {

	/**
	 * Initiates the algorithm.
	 */
	public void init(Grid grid);

	/**
	 * Does the next Step of the algorithm. Should be an operation that changes
	 * only a few GridElements states.
	 *
	 * @return true if a next step was found and executed, false otherwise.
	 *         Should return false if a solution was already found.
	 */
	public boolean doNextStep();

	/**
	 * Calculates if the algorithm finished and solved the way problem.
	 *
	 * @return true if the algorithm has finished and solved the problem, false
	 *         otherwise.
	 */
	public boolean isSolved();
}
