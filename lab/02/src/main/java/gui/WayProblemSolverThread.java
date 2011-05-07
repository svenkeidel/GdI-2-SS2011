/**
 * 
 */
package gui;

import java.security.InvalidParameterException;

import logic.WayProblemSolver;

/**
 * A class that encapsulates the WayProblemSolver class to let it run in a
 * different thread as the GUI.
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 * 
 */
public class WayProblemSolverThread extends Thread {

	private WayProblemSolver solver;

	public WayProblemSolverThread(WayProblemSolver solver) {
		if (solver != null)
			this.solver = solver;
		else
			throw new InvalidParameterException("The Solver can not be null");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		solver.solve();
	}

}
