package logic;

import java.security.InvalidParameterException;
import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.Logger;

import datamodel.Grid;
import datamodel.UpdateEvent;

import logic.algorithm.Algorithm;

/**
 * Holds the algorithm and the grid.<br>
 * Provides functionality to solve the grid with the given algorithm.
 *
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 *
 */
public class WayProblemSolver extends Observable {

	private static final Logger logger = Logger.getLogger(WayProblemSolver.class);
	private Algorithm algorithm;
	private Grid grid;
	private int delayTime;

	/**
	 * Constructor
	 *
	 * @param algorithm
	 *            the algorithm to use
	 * @param observer
	 * @param width
	 * @param height
	 * @param delayTime
	 *            the delay time in ms
	 */
	public WayProblemSolver(Algorithm algorithm, Observer observer, int width,
			int height, int delayTime) {
		if (algorithm != null)
			this.algorithm = algorithm;
		else
			throw new InvalidParameterException("You must specify an algorithm");

		if(observer != null)
			this.addObserver(observer);
		this.grid = new Grid(height, width, observer);
		this.delayTime = delayTime;
	}

	/**
	 * Constructor
	 *
	 * @param algorithm
	 *            the algorithm to use
	 * @param observer
	 * @param grid
	 * @param delayTime
	 *            the delay time in ms
	 */
	public WayProblemSolver(Algorithm algorithm, Observer observer, Grid grid, int delayTime) {
		if (algorithm != null)
			this.algorithm = algorithm;
		else
			throw new InvalidParameterException("You must specify an algorithm");

		if(observer != null)
			this.addObserver(observer);
		
		this.grid = new Grid(grid, observer);
		
		this.delayTime = delayTime;
	}

	/**
	 * Solves the problem.
	 *
	 * @return true if the problem was solved, false otherwise.
	 */
	public boolean solve() {
		if (!checkPreconditions()) {
			this.setChanged();
			this.notifyObservers(new UpdateEvent(
					UpdateEvent.FALSE_PRECONDITIONS, 0, 0, null, null));
			return false;
		}

		// init the algorithm
		algorithm.init(grid);

		// do the necessary steps till there are no more steps
		while (algorithm.doNextStep()) {
			if (delayTime != 0) {
				try {
					Thread.sleep(delayTime);
					// this.wait(delayTime);
				} catch (InterruptedException e) {
					// do nothing
				}
			}
		}

		boolean solved = algorithm.isSolved();
	
		if (solved) {
			showBestPath();
			this.setChanged();
			this.notifyObservers(new UpdateEvent(UpdateEvent.FOUND_SOLUTION, 0,
					0, null, null));
		} else {
			this.setChanged();
			this.notifyObservers(new UpdateEvent(UpdateEvent.NO_SOLUTION, 0, 0,
					null, null));
		}

		// return if the problem was solved
		return solved;
	}

	/**
	 * Checks if start and end are set.
	 *
	 * @return true if the start and end are set, false otherwise
	 */
	private boolean checkPreconditions() {
		if (grid.getStartElement() == null || grid.getEndElement() == null)
			return false;
		return true;
	}

	/**
	 * marks every GridElement on the best path with the GridElementAlgoState.PATH
	 */
	public void showBestPath() {

		//TODO: implement

		throw new UnsupportedOperationException("Implement me!");
	}

	/**
	 * @param algorithm
	 *            the algorithm to set
	 */
	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * @return the algorithm
	 */
	public Algorithm getAlgorithm() {
		return algorithm;
	}

	/**
	 * @return the grid
	 */
	public Grid getGrid() {
		return grid;
	}
}
